package de.travelinski

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun BackgroundComposable(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize().alpha(0.5f)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val wave = Path().apply {
                moveTo(0f, height * 0.7f)
                cubicTo(
                    width * 0.2f, height * 0.75f, width * 0.5f, height * 0.55f, width, height * 0.6f
                )
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }
            val diagonalGradient = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF90B8E2), // sehr helles Blau-Grau
                    Color(0xFFF6E7AC)  // sehr helles Creme
                ), start = Offset(0f, height),      // unten links
                end = Offset(width, 0f)          // oben rechts
            )
            val waveGradient = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF6AACF6).copy(alpha = 0.5f), // leichtes Pastellblau
                    Color(0xFFFFE181).copy(alpha = 0.4f)  // leichtes Pastellgelb
                ), start = Offset(0f, height), end = Offset(width, 0f)
            )
            drawRect(brush = diagonalGradient, size = size)
            drawPath(path = wave, brush = waveGradient)
        }
    }
}