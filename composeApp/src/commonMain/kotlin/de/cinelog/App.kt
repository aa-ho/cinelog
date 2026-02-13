package de.cinelog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import de.cinelog.data.Watchlist
import de.cinelog.data.WatchlistItem
import de.cinelog.utils.dummy.dummyCurrentUser
import org.koin.compose.koinInject

@Composable
fun App(viewModel: MainViewModel = koinInject()) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Movies) }

    var addButtonExpanded by remember { mutableStateOf(false) }
    var showRatingDialog by remember { mutableStateOf(false) }
    var showWatchlistDialog by remember { mutableStateOf(false) }
    var showAddToWatchlistDialog by remember { mutableStateOf(false) }


    var selectedFilmInWatchlist by remember { mutableStateOf<WatchlistItem?>(null) }
    var selectedWatchlist by remember { mutableStateOf<Watchlist?>(null) }

    val haptic = LocalHapticFeedback.current
    val hapticClick = remember {
        { haptic.performHapticFeedback(HapticFeedbackType.VirtualKey) }
    }

    val uiState by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadWatchlists(userId = "698c72b29416d629588ce5b3")
    }

    MaterialTheme(colorScheme = lightColorScheme()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar(
                    currentScreen = currentScreen,
                    onScreenChange = {
                        hapticClick()
                        if (addButtonExpanded) addButtonExpanded = false
                        currentScreen = it
                    },
                )
            },
            topBar = {
                val avatarUrl = when (uiState) {
                    is WatchlistUiState.Success -> (uiState as WatchlistUiState.Success).watchlists.firstOrNull()?.membersAvatars?.values?.firstOrNull()
                    else -> null
                }
                SettingsButtonComposable(
                    onClick = {}, screen = currentScreen, avatarUrl = avatarUrl,
                )
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize().clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) {
                    addButtonExpanded = false
                },
                contentAlignment = Alignment.BottomCenter,
            ) {
                BackgroundComposable()
                when (currentScreen) {
                    Screen.Movies -> {
                        MovieHomeScreenComposable(uiState = uiState)
                    }

                    else -> ComingSoonComposable(screen = currentScreen)
                }
            }
        }
    }
}