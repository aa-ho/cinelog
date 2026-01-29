package de.travelinski

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import travelinski.composeapp.generated.resources.Res
import travelinski.composeapp.generated.resources.oscar
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RatingSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    screen: Screen,
    modifier: Modifier = Modifier,
) {
    val rounded = value.roundToInt().coerceIn(1, 10)
    val rating = Rating.entries.first { it.score == rounded }
    val haptic = LocalHapticFeedback.current
    var lastIntValue by remember { mutableStateOf(rounded) } //TODO required or too many vibrations!
    Column(
        modifier = modifier.padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedText(rounded.toString())
        Surface(
            shape = CircleShape,
            shadowElevation = 5.dp,
        ) {
            RatingSlider2(
                rating = value.toInt(),
                onRatingChange = {
                    val coerced = it.coerceIn(1, 10)
                    if (coerced != lastIntValue) {
                        haptic.performHapticFeedback(HapticFeedbackType.VirtualKey)
                        lastIntValue = coerced
                    }
                    onValueChange(coerced.toFloat())
                },
                screen = screen,
            )
        }
        AnimatedText(rating.text)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedText(rating: String, small: Boolean = false) {
    var animateTrigger by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (animateTrigger) 1.3f else 1f, animationSpec = tween(
            durationMillis = 200, easing = FastOutSlowInEasing
        ), finishedListener = { animateTrigger = false })
    LaunchedEffect(rating) {
        animateTrigger = true
    }
    val textStyle = if(small) {
        MaterialTheme.typography.headlineSmall
    } else {
        MaterialTheme.typography.titleSmall
    }
    Text(
        text = rating,
        style = textStyle,
        modifier = Modifier.scale(scale),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingSlider2(
    rating: Int,
    onRatingChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    screen: Screen,
) {
    val steps = 9
    val floatValue = remember(rating) { (rating - 1).toFloat() / steps }
    Slider(value = floatValue, onValueChange = {
        val newRating = (it * 9f).roundToInt().coerceIn(0, steps) + 1
        onRatingChange(newRating)
    }, modifier = modifier.padding(horizontal = 12.dp), steps = steps, thumb = { state ->
        val animatedSize by animateDpAsState(
            targetValue = if (state.isDragging) 32.dp else 24.dp
        )
        Icon(
            modifier = Modifier.size(animatedSize),
            painter = painterResource(Res.drawable.oscar),
            contentDescription = null,
            tint = Color(0xFFB3820C),
        )
    }, track = { sliderState ->
        Box(
            Modifier.height(8.dp).fillMaxWidth()
        ) {
            val fraction =
                (sliderState.value - sliderState.valueRange.start) / (sliderState.valueRange.endInclusive - sliderState.valueRange.start)
            Box(
                Modifier.fillMaxWidth(fraction = fraction).fillMaxHeight().background(
                    brush = screen.gradient, shape = RoundedCornerShape(4.dp)
                )
            )/*            BoxWithConstraints(Modifier.fillMaxSize()) {
                            val widthPx = constraints.maxWidth.toFloat()
                            repeat(10) { index ->
                                val loc = (index / 9f) * widthPx
                                Column(Modifier.offset { IntOffset((loc - 6.dp.toPx()).toInt(), 0) }
                                    .width(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(
                                        Modifier.size(12.dp).background(
                                            if (index <= (fraction * 9).roundToInt()) Color(0xFFFF512F)
                                            else Color.LightGray, CircleShape
                                        )
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = (index + 1).toString(),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = if (index <= (fraction * 9).roundToInt()) Color(0xFFDD2476)
                                        else Color.Gray
                                    )
                                }
                            }
                        }*/
        }
    })
}