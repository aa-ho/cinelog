package de.cinelog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.cinelog.network.MovieApi
import de.cinelog.network.WatchlistResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

expect val ApplicationDispatcher: CoroutineDispatcher

sealed class WatchlistUiState {
    object Loading : WatchlistUiState()
    data class Success(val watchlists: List<WatchlistResponse>) : WatchlistUiState()
    data class Error(val message: String) : WatchlistUiState()
}

class MainViewModel(private val movieApi: MovieApi) : ViewModel() {
    private val _state = MutableStateFlow<WatchlistUiState>(WatchlistUiState.Loading)
    val state: StateFlow<WatchlistUiState> = _state
    fun loadWatchlists(userId: String) {
        viewModelScope.launch {
            _state.value = WatchlistUiState.Loading
            try {
                val result = withContext(ApplicationDispatcher) {
                    movieApi.getUserWatchlists(userId)
                }
                _state.value = WatchlistUiState.Success(result)
            } catch (e: Exception) {
                _state.value = WatchlistUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
