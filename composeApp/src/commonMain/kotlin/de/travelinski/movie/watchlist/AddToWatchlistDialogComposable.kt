package de.travelinski.movie.watchlist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.travelinski.ActionButton
import de.travelinski.BaseDialog
import de.travelinski.CustomSearchTextField
import de.travelinski.Screen
import de.travelinski.data.Watchlist


@Composable
fun AddToWatchlistDialogComposable(
    watchlist: Watchlist?,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    var titleValue by remember { mutableStateOf("") }
    if (watchlist == null) return

    BaseDialog(
        showDialog = showDialog, onDismiss = onDismiss,
        titleContent = {
            Text(
                watchlist.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        },
        bodyContent = {
            CustomSearchTextField("Enter title..", titleValue, { }, Screen.Movies)
            Row(Modifier.fillMaxWidth().padding(20.dp)) {
                ActionButton(
                    title = "Close",
                    modifier = Modifier.weight(1f),
                    onClick = onDismiss,
                    isPrimary = false,
                    screen = Screen.Movies,
                )
                Spacer(Modifier.width(15.dp))
                ActionButton(
                    title = "Add",
                    modifier = Modifier.weight(1f),
                    onClick = onConfirm,
                    screen = Screen.Movies,
                )
            }
        },
    )
}