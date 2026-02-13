package de.cinelog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.cinelog.data.Watchlist
import de.cinelog.data.WatchlistItem
import de.cinelog.movie.watchlist.WatchlistComposable
import io.ktor.util.logging.Logger

data class AddButtonActions(
    val onDismiss: () -> Unit,
    val onRateClick: () -> Unit,
    val onWatchlistClick: () -> Unit,
)

data class RatingDialogActions(
    val show: Boolean,
    val onDismiss: () -> Unit,
    val onConfirm: () -> Unit,
    val onRateSliderClick: () -> Unit,
)

data class WatchlistDialogActions(
    val show: Boolean,
    val onDismiss: () -> Unit,
    val onConfirm: () -> Unit,
    val onShow: (WatchlistItem) -> Unit,
    val onWantToWatchToggleClick: (Boolean) -> Unit, //TODO! hmm
)

data class AddToWatchlistDialogActions(
    val show: Boolean,
    val onDismiss: () -> Unit,
    val onConfirm: () -> Unit,
    val onShow: (Watchlist) -> Unit, //TODO! hmm
)

@Composable
fun MovieHomeScreenComposable(uiState: WatchlistUiState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        when (uiState) {
            is WatchlistUiState.Loading -> CircularProgressIndicator()
            is WatchlistUiState.Error -> {
                Box(Modifier.fillMaxSize().padding(vertical = 80.dp, horizontal = 15.dp)) {
                    Text("Error: ${uiState.message}")
                }
            }

            is WatchlistUiState.Success -> {
                if (uiState.watchlists.isEmpty()) {
                    Text("No watchlists yet")
                } else {/*                    WatchlistDialogComposable(
                                            showDialog = watchlistDialog.show,
                                            onDismiss = watchlistDialog.onDismiss,
                                            onConfirm = watchlistDialog.onConfirm,
                                            watchlistItemData = selectedFilm,
                                            onRateClicked = addActions.onRateClick,
                                            onWantToWatchToggleClick = watchlistDialog.onWantToWatchToggleClick,
                                            currentUser = dummyCurrentUser(),
                                        )*/
                    Column(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
                            Text(
                                text = "Watchlists",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        LazyColumn {
                            itemsIndexed(items = uiState.watchlists) { _, watchlist ->
                                WatchlistComposable(
                                    watchlist = watchlist,
                                    onShowDialog = {},
                                    onShowAddDialog = {},
                                )
                                Spacer(Modifier.padding(vertical = 10.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}