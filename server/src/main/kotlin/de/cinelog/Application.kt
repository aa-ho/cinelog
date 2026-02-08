package de.cinelog

import com.mongodb.client.model.Filters.`in`
import de.cinelog.routes.movieRoutes
import de.cinelog.routes.respondUserWatchlists
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = ObjectId::class)
object ObjectIdSerializer : KSerializer<ObjectId> {
    override val descriptor = PrimitiveSerialDescriptor("ObjectId", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: ObjectId) =
        encoder.encodeString(value.toHexString())

    override fun deserialize(decoder: Decoder) = ObjectId(decoder.decodeString())
}

@Serializable
data class Movie(
    val title: String,
    val iMDbUrl: String?,
    val rottenTomatoesUrl: String?,
    val duration: String?,
    val releaseDate: String?,
    @Contextual val directorId: ObjectId?,
    val coverUrl: String?,
)

@Serializable
data class Watchlist(
    @Contextual val _id: ObjectId,
    val title: String,
    val members: List<@Contextual ObjectId>,
    val movies: List<WatchlistMovie> = emptyList(),
)

@Serializable
data class WatchlistMovie(
    @Contextual val movieId: ObjectId,
    val dateAdded: String,
    @Contextual val addedBy: ObjectId? = null,
)

@Serializable
data class User(
    @Contextual val _id: ObjectId,
    val name: String,
    val avatarUrl: String?,
    val watchlists: List<@Contextual ObjectId>,
)

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

fun main() {
    val mongoHost = System.getenv("MONGO_HOST") ?: "localhost"
    val client = KMongo.createClient("mongodb://$mongoHost:27017").coroutine

    val database = client.getDatabase("cinelog")
    val moviesCollection = database.getCollection<Movie>("movies")
    val watchlistsCollection = database.getCollection<Watchlist>("watchlists")
    val usersCollection = database.getCollection<User>("users")

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) {
            json(
                Json {
                    serializersModule = SerializersModule {
                        contextual(ObjectId::class, ObjectIdSerializer)
                    }
                })
        }
        routing {
            get("/") {
                call.respondText("CineLog: Ktor MongoDB Server is running")
            }
            movieRoutes(moviesCollection)
            get("/users/{userId}/watchlists") {
                call.respondUserWatchlists(
                    usersCollection = usersCollection, watchlistsCollection = watchlistsCollection
                )
            }
        }
    }.start(wait = true)
}