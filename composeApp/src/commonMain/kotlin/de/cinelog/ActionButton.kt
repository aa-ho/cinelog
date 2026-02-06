package de.cinelog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ActionButton(
    modifier: Modifier,
    title: String,
    onClick: () -> Unit,
    isPrimary: Boolean = true,
    screen: Screen,
) {
    Surface(
        modifier = modifier.shadow(1.dp, RoundedCornerShape(50)),
        shape = RoundedCornerShape(50),
    ) {
        val background = if (isPrimary) Modifier.background(screen.gradient) else Modifier
        val textColor = if (isPrimary) Color.White else Color.Unspecified
        Box(modifier = Modifier.then(background).clickable { onClick() }
            .padding(vertical = 10.dp, horizontal = 20.dp), contentAlignment = Alignment.Center) {
            Text(
                text = title, fontSize = 18.sp,
                color = textColor,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}