package de.cinelog.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import kotlinx.serialization.Serializable

class MovieApiImpl(private val client: HttpClient) : MovieApi {
    override suspend fun getUserWatchlists(userId: String): List<WatchlistResponse> {
        return client.get {
            url {
                protocol = URLProtocol.HTTP
                host = "85.215.157.220"
                port = 8080
                path("/users/$userId/watchlists")
            }
        }.body()
    }
}
