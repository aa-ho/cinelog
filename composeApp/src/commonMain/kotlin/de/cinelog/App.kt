package de.cinelog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import de.cinelog.data.Watchlist
import de.cinelog.data.WatchlistItem
import de.cinelog.utils.dummy.dummyCurrentUser
import de.cinelog.utils.dummy.dummyWatchlist
import de.cinelog.utils.dummy.privateWatchlist

@Preview
@Composable
fun App() {
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
                SettingsButtonComposable(
                    onClick = {}, screen = currentScreen, currentUser = dummyCurrentUser(),
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
                        MovieHomeScreenComposable(
                            addButtonExpanded = addButtonExpanded,
                            addActions = AddButtonActions(
                                onDismiss = {
                                    hapticClick()
                                    addButtonExpanded = !addButtonExpanded
                                },
                                onRateClick = {
                                    hapticClick()
                                    showRatingDialog = true
                                    addButtonExpanded = false
                                },
                                onWatchlistClick = {},
                            ),
                            ratingDialog = RatingDialogActions(
                                show = showRatingDialog,
                                onDismiss = {
                                    hapticClick()
                                    showRatingDialog = false
                                },
                                onConfirm = {},
                                onRateSliderClick = {},
                            ),
                            watchlistDialog = WatchlistDialogActions(
                                show = showWatchlistDialog,
                                onShow = { film -> //TODO fix rating  etc
                                    hapticClick()
                                    selectedFilmInWatchlist = film
                                    showWatchlistDialog = true
                                },
                                onDismiss = {
                                    hapticClick()
                                    showWatchlistDialog = false
                                },
                                onConfirm = {
                                    hapticClick()
                                    showWatchlistDialog = false
                                },
                                onWantToWatchToggleClick = { wantToWatch -> }, //TODO
                            ),
                            screen = currentScreen,
                            innerPadding = innerPadding,
                            watchLists = listOf(dummyWatchlist, privateWatchlist),
                            selectedFilm = selectedFilmInWatchlist,
                            addToWatchlistDialog = AddToWatchlistDialogActions(
                                show = showAddToWatchlistDialog,
                                onDismiss = {
                                    hapticClick()
                                    showAddToWatchlistDialog = false
                                },
                                onConfirm = {
                                    hapticClick()
                                    showAddToWatchlistDialog = false
                                },
                                onShow = { watchlist ->
                                    hapticClick()
                                    selectedWatchlist = watchlist
                                    showAddToWatchlistDialog = true
                                },
                            ),
                            selectedWatchlist = selectedWatchlist,
                        )
                    }

                    else -> ComingSoonComposable(screen = currentScreen)
                }
            }
        }
    }
}