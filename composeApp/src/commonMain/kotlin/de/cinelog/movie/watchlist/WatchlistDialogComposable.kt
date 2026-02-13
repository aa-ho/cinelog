package de.cinelog.movie.watchlist

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cinelog.composeapp.generated.resources.Res
import cinelog.composeapp.generated.resources.dummy_avatar
import cinelog.composeapp.generated.resources.imdb
import cinelog.composeapp.generated.resources.rotten_tomatoes
import compose.icons.TablerIcons
import compose.icons.tablericons.Plus
import de.cinelog.ActionButton
import de.cinelog.data.MediumType
import de.cinelog.Screen
import de.cinelog.data.User
import de.cinelog.data.WatchlistItem
import de.cinelog.gradient
import de.cinelog.utils.formatDuration
import de.cinelog.utils.formatSmartDate
import de.cinelog.utils.formatSmartDateTime
import de.cinelog.utils.simpleFormatDate
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


/*@Composable
fun WatchlistDialogComposable(
    showDialog: Boolean,
    watchlistItemData: WatchlistItem?,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onRateClicked: () -> Unit,
    currentUser: User,
    onWantToWatchToggleClick: (Boolean) -> Unit,
) {
    LocalUriHandler.current
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                modifier = Modifier.fillMaxWidth().shadow(2.dp, RoundedCornerShape(10)),
                shape = RoundedCornerShape(10),
            ) {
                if (watchlistItemData != null) {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        stickyHeader {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.background).padding(
                                        start = 20.dp, end = 20.dp, top = 20.dp, bottom = 10.dp
                                    ),
                                contentAlignment = Alignment.Center,
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = watchlistItemData.movieData.title,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                    )
                                    Text(
                                        text = "Added ${formatSmartDate(watchlistItemData.dateAdded)}, last modified ${
                                            formatSmartDateTime(
                                                watchlistItemData.lastModified
                                            )
                                        }",
                                        fontSize = 12.sp,
                                    )
                                }
                            }
                        }
                        item {
                            InfoRow(label = "Added by") {
                                Avatar(watchlistItemData.addedBy)
                            }
                            InfoRow("Want to watch") {
                                val containsCurrentUser =
                                    watchlistItemData.wantToWatch.contains(currentUser)
                                ToggleButton(
                                    add = !containsCurrentUser,
                                    onClick = { onWantToWatchToggleClick(!containsCurrentUser) })
                                if (watchlistItemData.wantToWatch.isNotEmpty()) {
                                    watchlistItemData.wantToWatch.forEach { wantToWatch ->
                                        Avatar(wantToWatch)
                                    }
                                }
                            }
                            InfoRow("Release") {
                                Text(simpleFormatDate(watchlistItemData.movieData.releaseDate))
                            }
                            InfoRow("Duration") {
                                Text(formatDuration(watchlistItemData.movieData.duration))
                            }
                            InfoRow("Director") {
                                Text(watchlistItemData.movieData.director.name)
                            }
                            val imdbUrl = watchlistItemData.movieData.iMDbUrl
                            val rtUrl = watchlistItemData.movieData.rottenTomatoesUrl
                            if (imdbUrl != null || rtUrl != null) {
                                InfoRow("Open in") {
                                    imdbUrl?.let { LinkImage(Res.drawable.imdb, it) }
                                    if (imdbUrl != null) Spacer(Modifier.width(5.dp))
                                    rtUrl?.let { LinkImage(Res.drawable.rotten_tomatoes, it) }
                                }
                            }
                            SectionDivider("Watched it already")
                            watchlistItemData.usersWatchedAlready.forEach { userWatched ->
                                InfoRow(label = userWatched.user.name, verticalPadding = 2.dp) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        if (userWatched.rating != null) {
                                            Text("${userWatched.rating}/10")
                                            Spacer(Modifier.padding(horizontal = 5.dp))
                                        }
                                        Avatar(userWatched.user)
                                    }
                                }
                            }
                            InfoRow("Watched it too?") {
                                Row(modifier = Modifier) {
                                    ToggleButton(add = true, onClick = onRateClicked)
                                }
                            }

                            SectionDivider("Owns this movie")
                            if (watchlistItemData.usersOwningMovie.isNotEmpty()) {
                                InfoRow("Has it") {
                                    watchlistItemData.usersOwningMovie.forEach { userOwning ->
                                        Avatar(userOwning.user)
                                    }
                                }
                                InfoRow(
                                    label = "Medium", maxItemsInEachRow = 3,
                                ) {
                                    val displayedMediums = mutableSetOf<MediumType>()
                                    watchlistItemData.usersOwningMovie.forEach { userOwning ->
                                        val medium = userOwning.mediumType
                                        if (medium in displayedMediums) return@forEach
                                        displayedMediums += medium
                                        if (medium.res != null) {
                                            LinkImage(medium.res, medium.url)
                                        } else {
                                            LinkText(
                                                text = medium.name.lowercase()
                                                    .replaceFirstChar { it.uppercase() },
                                                url = medium.url,
                                            )
                                        }
                                    }
                                }
                                InfoRow("Own it too?") {
                                    Row(modifier = Modifier) {
                                        ToggleButton(add = true, onClick = {})
                                    }
                                }
                            } else {
                                InfoRow("You own it?") {
                                    Row(modifier = Modifier) {
                                        ToggleButton(add = true, onClick = {})
                                    }
                                }
                            }
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
                                    title = "Done",
                                    modifier = Modifier.weight(1f),
                                    onClick = onConfirm,
                                    isPrimary = true,
                                    screen = Screen.Movies,
                                )
                            }
                        }
                    }
                } else {
                    PlaceHolderComposable(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.9f))
                }
            }
        }
    }
}*/

@Composable
fun PlaceHolderComposable(modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val shimmerTranslate by infiniteTransition.animateFloat(
        0f, 1000f, animationSpec = infiniteRepeatable(tween(1000))
    )
    val brush = Brush.linearGradient(
        colors = listOf(Color.LightGray, Color.White, Color.LightGray),
        start = Offset(shimmerTranslate, 0f),
        end = Offset(shimmerTranslate + 200f, 0f)
    )
    Box(modifier = modifier.background(brush))
}

@Composable
private fun SectionDivider(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth().height(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.weight(0.5f))
        HorizontalDivider(Modifier.weight(1f))
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = 5.dp),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        HorizontalDivider(Modifier.weight(1f))
        Spacer(Modifier.weight(0.5f))
    }
}

@Composable
private fun ToggleButton(
    add: Boolean,
    onClick: () -> Unit = {},
) {
    val angle by animateFloatAsState(
        targetValue = if (add) 0f else 45f, label = "rotation",
    )
    Box(
        modifier = Modifier.size(30.dp).background(Screen.Movies.gradient, CircleShape, 0.3f)
            .clip(CircleShape).padding(2.dp).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = TablerIcons.Plus,
            contentDescription = null,
            modifier = Modifier.rotate(angle)
        )
    }
}

@Composable
fun Avatar(
    avatarUrl: String?, size: Dp = 30.dp, onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.padding(2.dp).size(size).clip(CircleShape).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (avatarUrl == null) {
            Image(
                painter = painterResource(avatarUrl ?: Res.drawable.dummy_avatar),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier.background(Screen.Movies.gradient, CircleShape),
            )
        } else {
            val density = LocalDensity.current
            val maxPx = with(LocalDensity.current) { size.toPx().toInt() * 3 }
            val resource: Resource<Painter> = asyncPainterResource(
                data = avatarUrl,
                maxBitmapDecodeSize = IntSize(maxPx, maxPx)
            )
            KamelImage(
                modifier = Modifier.size(size),
                resource = { resource },
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                animationSpec = tween(durationMillis = 300),
            )
        }
    }
}

@Composable
private fun LinkImage(res: DrawableResource, url: String?) {
    val uriHandler = LocalUriHandler.current //TODO hmm
    Image(
        painter = painterResource(res),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier.height(30.dp).clip(RoundedCornerShape(8.dp))
            .clickable { url?.let { uriHandler.openUri(it) } })
}

@Composable
private fun LinkText(text: String, url: String) {
    val uriHandler = LocalUriHandler.current
    Text(text = text, modifier = Modifier.clickable { uriHandler.openUri(url) })
}


@Composable
private fun InfoRow(
    label: String,
    verticalPadding: Dp = 8.dp,
    maxItemsInEachRow: Int = 4,
    content: @Composable RowScope.() -> Unit,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = verticalPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.weight(1f))
            FlowRow(
                horizontalArrangement = Arrangement.End,
                maxItemsInEachRow = maxItemsInEachRow,
            ) {
                content()
            }
        }
        Spacer(Modifier.height(2.dp))
    }
}