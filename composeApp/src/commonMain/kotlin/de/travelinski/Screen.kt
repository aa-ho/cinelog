package de.travelinski

import org.jetbrains.compose.resources.DrawableResource
import travelinski.composeapp.generated.resources.Res
import travelinski.composeapp.generated.resources.home_icon
import travelinski.composeapp.generated.resources.movie
import travelinski.composeapp.generated.resources.restaurant_icon
import travelinski.composeapp.generated.resources.tv

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