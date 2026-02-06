package de.cinelog

import cinelog.composeapp.generated.resources.Res
import cinelog.composeapp.generated.resources.home_icon
import cinelog.composeapp.generated.resources.movie
import cinelog.composeapp.generated.resources.restaurant_icon
import cinelog.composeapp.generated.resources.tv
import org.jetbrains.compose.resources.DrawableResource

sealed class Screen(
    val label: String,
    val resource: DrawableResource,
) {
    object Movies : Screen(label = "Movies", resource = Res.drawable.movie)
    object TVShows : Screen(label = "TV-Shows", resource = Res.drawable.tv)
    object Home : Screen(label = "Home", resource = Res.drawable.home_icon)

    object Restaurants : Screen(label = "Restaurants", resource = Res.drawable.restaurant_icon)
    //object More : Screen(label = "More", resource = Res.drawable.more)
}