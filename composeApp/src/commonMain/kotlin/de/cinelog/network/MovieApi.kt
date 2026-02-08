package de.cinelog.network

import kotlinx.serialization.Serializable

@Serializable
data class WatchlistMovieResponse(
    val movieId: String,
    val addedBy: String?,
    val addedByAvatar: String?,
)

@Serializable
data class WatchlistResponse(
    val watchlistId: String,
    val title: String,
    val members: List<String>,
    val membersAvatars: Map<String, String?>,
    val movies: List<WatchlistMovieResponse>,
)

interface MovieApi {
    suspend fun getUserWatchlists(userId: String): List<WatchlistResponse>
}