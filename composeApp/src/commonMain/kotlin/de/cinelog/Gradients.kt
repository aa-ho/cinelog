package de.cinelog

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Screen.gradient: Brush
    get() = when (this) {
        Screen.Movies -> Brush.horizontalGradient(
            listOf(
                Color(0xFFFF512F),
                Color(0xFFDD2476),
            )
        )

        Screen.TVShows -> Brush.horizontalGradient(
            listOf(
                Color(0xFF24C6DC),
                Color(0xFF514A9D),
            )
        )

        Screen.Home -> Brush.horizontalGradient(
            listOf(
                Color(-0x4f413b),
                Color(-0x13100f),
            )
        )

        Screen.Restaurants -> Brush.horizontalGradient(
            listOf(
                Color(0xFFFFD200),
                Color(0xFFF7971E),
            )
        )
    }