package de.cinelog.data

import cinelog.composeapp.generated.resources.Res
import cinelog.composeapp.generated.resources.blu_ray
import cinelog.composeapp.generated.resources.dvd
import cinelog.composeapp.generated.resources.ultra_hd_bluray
import cinelog.composeapp.generated.resources.vhs
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import kotlin.time.Duration
import kotlin.uuid.ExperimentalUuidApi

enum class MediumType(val url: String, val res: DrawableResource? = null) {
    ULTRA_HD_BLU_RAY(
        url = "https://de.wikipedia.org/wiki/Ultra_HD_Blu-ray",
        res = Res.drawable.ultra_hd_bluray,
    ),
    BLU_RAY(
        url = "https://de.wikipedia.org/wiki/Blu-ray",
        res = Res.drawable.blu_ray,
    ),
    DVD(
        url = "https://de.wikipedia.org/wiki/DVD",
        res = Res.drawable.dvd,
    ),
    VHS(
        url = "https://de.wikipedia.org/wiki/Video_Home_System",
        res = Res.drawable.vhs,
    ),
    ONLINE(
        url = "https://de.wikipedia.org/wiki/Video-on-Demand",
    )
}


@OptIn(ExperimentalUuidApi::class)
data class MovieData(
    val uuid: String,
    val title: String,
    val iMDbUrl: String? = null,
    val rottenTomatoesUrl: String? = null,
    val duration: Duration,
    val releaseDate: LocalDate,
    val director: Director,
    val coverUrl: String? = null,
)

data class Director(
    val name: String,
    val movies: String? = null,
    val age: String? = null,
)

@OptIn(ExperimentalUuidApi::class)
data class User(
    val name: String,
    val avatarUrl: String? = null,
)

data class UserWatchedAlready(
    val user: User,
    val rating: Int? = null,
)

data class UserOwningMovie(
    val user: User,
    val mediumType: MediumType,
)

data class WatchlistItem(
    val movieData: MovieData,
    val dateAdded: LocalDate,
    val lastModified: LocalDateTime,
    val addedBy: User,
    val wantToWatch: List<User>,
    val usersWatchedAlready: List<UserWatchedAlready>,
    val usersOwningMovie: List<UserOwningMovie>,
)

data class Watchlist(
    val member: List<User>,
    val title: String,
    val movies: List<WatchlistItem?>,
)