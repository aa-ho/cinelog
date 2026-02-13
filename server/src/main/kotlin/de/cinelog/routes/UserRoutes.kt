package de.cinelog.routes

import com.mongodb.client.model.Filters.`in`
import de.cinelog.User
import de.cinelog.Watchlist
import de.cinelog.WatchlistMovieResponse
import de.cinelog.WatchlistResponse
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineCollection

suspend fun ApplicationCall.respondUserWatchlists(
    usersCollection: CoroutineCollection<User>,
    watchlistsCollection: CoroutineCollection<Watchlist>,
) {
    val userIdParam = parameters["userId"] ?: return respondText("Missing userId")
    val userId = try {
        ObjectId(userIdParam)
    } catch (_: IllegalArgumentException) {
        return respondText("Invalid userId")
    }
    val user = usersCollection.findOneById(userId) ?: return respondText("User not found")
    val allUserIds = mutableSetOf<ObjectId>()
    allUserIds.addAll(user.watchlists)
    val watchlistsFromDb = watchlistsCollection.find(
        `in`("_id", user.watchlists)
    ).toList()
    watchlistsFromDb.forEach { wl ->
        allUserIds.addAll(wl.members)
        wl.movies.forEach { m -> m.addedBy?.let { allUserIds.add(it) } }
    }
    val usersMap = usersCollection.find(`in`("_id", allUserIds.toList())).toList()
        .associateBy({ it._id }, { it.avatarUrl })
    val watchlistsWithMovies = watchlistsFromDb.map { wl ->
        WatchlistResponse(
            watchlistId = wl._id.toHexString(),
            title = wl.title,
            members = wl.members.map { it.toHexString() },
            membersAvatars = wl.members.associate { it.toHexString() to usersMap[it] },
            movies = wl.movies.map { m ->
                WatchlistMovieResponse(
                    movieId = m.movieId.toHexString(),
                    addedBy = m.addedBy?.toHexString(),
                )
            },
        )
    }
    respond(watchlistsWithMovies)
}