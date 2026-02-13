package de.cinelog.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

//expect fun createHttpClient(): HttpClient

object NetworkModule {
    fun provideHttpClient() = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
        install(HttpTimeout) {
            socketTimeoutMillis = 15_000
            connectTimeoutMillis = 10_000
            requestTimeoutMillis = 15_000
        }
    }

    fun providePostApi(client: HttpClient): MovieApi {
        return MovieApiImpl(client)
    }
}