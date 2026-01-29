package de.travelinski

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.travelinski.movie.watchlist.Avatar

@Preview
@Composable
fun SettingsButtonComposable(onClick: () -> Unit, screen: Screen, currentUser: User) {
    Row(Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 50.dp)) {
        Spacer(Modifier.weight(1f))
        Surface(
            modifier = Modifier.size(40.dp),
            onClick = {},
            shape = CircleShape, shadowElevation = 5.dp,
        ) {
            Box(
                modifier = Modifier.background(
                    brush = screen.gradient, shape = CircleShape
                ),
                contentAlignment = Alignment.Center,
            ) {
                Avatar(size = 39.dp, user = currentUser)
            }
        }
    }
}