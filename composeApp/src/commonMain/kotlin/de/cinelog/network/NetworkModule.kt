package de.cinelog.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

object NetworkModule {
    fun provideHttpClient(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    fun providePostApi(client: HttpClient): MovieApi {
        return MovieApiImpl(client)
    }
}