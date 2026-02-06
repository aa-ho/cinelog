package de.cinelog

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.Serializable
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

@Serializable
data class Movie(
    val uuid: String,
    val title: String,
    val year: Int,
)

fun main() {
    val client = KMongo.createClient("mongodb://localhost:27017").coroutine
    val database = client.getDatabase("cinelog")
    val collection = database.getCollection<Movie>("movies")
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) {
            json()
        }
        routing {
            get("/") {
                call.respondText("Ktor MongoDB Server läuft!")
            }
            get("/movies") {
                val movies = collection.find().toList()
                call.respond(movies)
            }
        }
    }.start(wait = true)
}