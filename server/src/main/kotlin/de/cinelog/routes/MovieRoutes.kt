package de.cinelog.routes

import de.cinelog.Movie
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineCollection

fun Route.movieRoutes(moviesCollection: CoroutineCollection<Movie>) {
    route("/movies") {
        get {
            call.respond(moviesCollection.find().toList())
        }
        get("{id}") {
            val idParam = call.parameters["id"] ?: return@get call.respond(
                HttpStatusCode.BadRequest, "Missing id"
            )
            val objectId = try {
                ObjectId(idParam)
            } catch (_: IllegalArgumentException) {
                return@get call.respond(HttpStatusCode.BadRequest, "Invalid id format")
            }
            val movie = moviesCollection.findOneById(objectId)
            if (movie != null) {
                call.respond(movie)
            } else {
                call.respond(HttpStatusCode.NotFound, "Movie not found")
            }
        }
    }
}