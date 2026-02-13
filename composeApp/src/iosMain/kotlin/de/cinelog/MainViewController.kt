package de.cinelog

import androidx.compose.ui.window.ComposeUIViewController
import appModule
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object Injector : KoinComponent
fun MainViewController() = ComposeUIViewController {
    startKoin { modules(appModule) }
    val viewModel: MainViewModel = Injector.get()
    App(viewModel)
}