package de.cinelog

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RateMovieDialogComposable(
    featureEnabled: Boolean,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    providedTitle: String?,
) {
    var titleValue by remember { mutableStateOf("") }
    var imdbValue by remember { mutableStateOf("") }
    var ratingValue by remember { mutableStateOf(7F) }

    BaseDialog(showDialog = showDialog, onDismiss = onDismiss, titleContent = {
        if (providedTitle.isNullOrEmpty()) {
            Text(
                "Rate Movie",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Rate",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(providedTitle, fontSize = 16.sp)
            }
        }
    }, bodyContent = {
        if (featureEnabled) {
            RatingSlider(
                value = ratingValue,
                onValueChange = { ratingValue = it },
                screen = Screen.Movies,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            if (providedTitle.isNullOrEmpty()) {
                CustomSearchTextField(
                    "Enter title..", titleValue, { titleValue = it }, Screen.Movies
                )
            }
            CustomSearchTextField(
                "Write a review..", imdbValue, { imdbValue = it }, Screen.Movies, singleLine = false
            )
            Row(Modifier.fillMaxWidth().padding(20.dp)) {
                ActionButton(
                    title = "Close",
                    modifier = Modifier.weight(1f),
                    onClick = onDismiss,
                    isPrimary = false,
                    screen = Screen.Movies
                )
                Spacer(Modifier.width(15.dp))
                ActionButton(
                    title = "Done",
                    modifier = Modifier.weight(1f),
                    onClick = onConfirm,
                    screen = Screen.Movies
                )
            }
        } else {
            Text(
                "Coming soon",
                Modifier.padding(vertical = 20.dp),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    })
}