package de.cinelog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.cinelog.Screen.Movies
import de.cinelog.Screen.Restaurants
import de.cinelog.Screen.TVShows
import org.jetbrains.compose.resources.painterResource

@Composable
fun dummyColor() = Color(0x7CBF2D2D)


@Preview
@Composable
fun NavigationBar(currentScreen: Screen, onScreenChange: (Screen) -> Unit) {
    val screens = listOf(Movies, TVShows, Restaurants)
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 20.dp),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier.height(80.dp).shadow(3.dp, RoundedCornerShape(50)),
            shape = RoundedCornerShape(70),
        ) {
            Row(
                modifier = Modifier.wrapContentWidth(), horizontalArrangement = Arrangement.Center,
            ) {
                Spacer(Modifier.padding(5.dp))
                screens.forEach { screen ->
                    val isSelected = screen == currentScreen
                    Box(
                        Modifier.fillMaxHeight().padding(5.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        val modifier = if (isSelected) Modifier.shadow(
                            3.dp, RoundedCornerShape(50)
                        ) else Modifier
                        Surface(
                            modifier = modifier, shape = RoundedCornerShape(50)
                        ) {
                            val padding = if (isSelected) PaddingValues(
                                horizontal = 20.dp, vertical = 5.dp
                            ) else PaddingValues(horizontal = 6.dp, vertical = 5.dp)
                            val brush = if (isSelected) Modifier.background(screen.gradient)
                            else Modifier
                            Column(
                                modifier = Modifier.wrapContentHeight().then(brush).padding(padding)
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() },
                                    ) {
                                        onScreenChange(screen)
                                    },
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                val color =
                                    if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.secondary
                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    painter = painterResource(screen.resource),
                                    contentDescription = null,
                                    tint = color,
                                )
                                Text(
                                    text = screen.label,
                                    style = MaterialTheme.typography.labelSmall,
                                    lineHeight = 1.sp,
                                    maxLines = 1,
                                    softWrap = false,
                                    color = color,
                                )
                            }
                        }
                    }
                    Spacer(Modifier.padding(5.dp))
                }
            }
        }
    }
}