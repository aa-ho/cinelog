package de.travelinski

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import compose.icons.TablerIcons
import compose.icons.tablericons.Plus

@Preview
@Composable
fun CreateButton(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onRateTap: () -> Unit,
    onWatchlistTap: () -> Unit,
) {
    Box(modifier = Modifier.padding(15.dp)) {
        val shape = if (expanded) 20 else 50
        Surface(
            modifier = Modifier.shadow(2.dp, RoundedCornerShape(shape)).animateContentSize(),
            shape = RoundedCornerShape(shape),
        ) {
            AnimatedVisibility(
                visible = !expanded,
                enter = fadeIn(animationSpec = tween(100)),
                exit = fadeOut(animationSpec = tween(100)),
            ) {
                Icon(
                    modifier = Modifier.clickable { onDismiss() }.padding(15.dp).size(25.dp),
                    imageVector = TablerIcons.Plus,
                    contentDescription = null,
                )
            }
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(animationSpec = tween(100)),
                exit = fadeOut(animationSpec = tween(100)),
            ) {
                Column {
                    Text(
                        modifier = Modifier.width(100.dp).clickable { onRateTap() }.padding(10.dp),
                        text = "Rate",
                    )
                    Text(
                        modifier = Modifier.width(100.dp).clickable { onWatchlistTap() }
                            .padding(10.dp),
                        text = "Watchlist",
                    )
                }
            }
        }
    }
}