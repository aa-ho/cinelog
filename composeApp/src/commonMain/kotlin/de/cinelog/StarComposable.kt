package de.cinelog

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Preview
@Composable
fun StarComposable(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            val r = size.minDimension / 2
            val c = Offset(r, r)
            for (i in 0..4) {
                val a1 = (i * 72f - 90f) * PI.toFloat() / 180f
                val a2 = ((i * 72f + 36f) - 90f) * PI.toFloat() / 180f
                if (i == 0) moveTo(c.x + cos(a1) * r, c.y + sin(a1) * r)
                else lineTo(c.x + cos(a1) * r, c.y + sin(a1) * r)
                lineTo(c.x + cos(a2) * r * 0.5f, c.y + sin(a2) * r * 0.5f)
            }
            close()
        }
        drawPath(path, color = Color(0xFF7D6400))
    }
}