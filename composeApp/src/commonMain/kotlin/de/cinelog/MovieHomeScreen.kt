package de.cinelog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.cinelog.data.Watchlist
import de.cinelog.data.WatchlistItem
import de.cinelog.movie.watchlist.AddToWatchlistDialogComposable
import de.cinelog.movie.watchlist.WatchlistComposable
import de.cinelog.movie.watchlist.WatchlistDialogComposable
import de.cinelog.utils.dummy.dummyCurrentUser

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

@Preview
@Composable
fun MovieHomeScreenComposable(
    addButtonExpanded: Boolean,
    addActions: AddButtonActions,
    ratingDialog: RatingDialogActions,
    watchlistDialog: WatchlistDialogActions,
    addToWatchlistDialog: AddToWatchlistDialogActions, //TODO big object
    screen: Screen,
    innerPadding: PaddingValues,
    watchLists: List<Watchlist?>,
    selectedFilm: WatchlistItem?,
    selectedWatchlist: Watchlist?, //TODO really ugly!!!
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        RateMovieDialogComposable(
            showDialog = ratingDialog.show,
            onDismiss = ratingDialog.onDismiss,
            onConfirm = ratingDialog.onConfirm,
            providedTitle = selectedFilm?.movieData?.title,
            featureEnabled = false, //TODO!
        )
        WatchlistDialogComposable(
            showDialog = watchlistDialog.show,
            onDismiss = watchlistDialog.onDismiss,
            onConfirm = watchlistDialog.onConfirm,
            watchlistItemData = selectedFilm,
            onRateClicked = addActions.onRateClick,
            onWantToWatchToggleClick = watchlistDialog.onWantToWatchToggleClick,
            currentUser = dummyCurrentUser(),
        )
        AddToWatchlistDialogComposable(
            watchlist = selectedWatchlist, //TODO hmm
            showDialog = addToWatchlistDialog.show,
            onDismiss = addToWatchlistDialog.onDismiss,
            onConfirm = addToWatchlistDialog.onConfirm,
        )/*        Row(
                    modifier = Modifier.fillMaxWidth().padding(innerPadding),
                    horizontalArrangement = Arrangement.End
                ) {
                    CreateButton(
                        expanded = addButtonExpanded,
                        onDismiss = addActions.onDismiss,
                        onRateTap = addActions.onRateClick,
                        onWatchlistTap = addActions.onWatchlistClick,
                    )
                }*/
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
            watchLists.forEach { watchlist ->
                WatchlistComposable(
                    watchlist = watchlist,
                    onShowDialog = watchlistDialog.onShow,
                    onShowAddDialog = {
                        watchlist?.let { addToWatchlistDialog.onShow(it) }
                    },
                )
                Spacer(Modifier.padding(vertical = 10.dp))
            }
        }
    }
}