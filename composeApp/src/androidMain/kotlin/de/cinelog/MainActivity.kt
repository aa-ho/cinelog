package de.cinelog

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import de.cinelog.network.MovieApi
import de.cinelog.network.NetworkModule
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    suspend fun printAllMovies(api: MovieApi, userId: String) {
        val watchlists = api.getUserWatchlists(userId)
        watchlists.forEach { wl ->
            Log.e("Watchlist", "📝 Watchlist: ${wl.title} (ID: ${wl.watchlistId})")
            wl.membersAvatars.forEach { (memberId, avatarUrl) ->
                Log.e("Watchlist", "   Member: $memberId, Avatar: ${avatarUrl ?: "No avatar"}")
            }
            wl.movies.forEach { movie ->
                Log.e(
                    "Watchlist",
                    "   🎬 Movie ID: ${movie.movieId}" + "by ${movie.addedBy ?: "Unknown"} (Avatar: ${movie.addedByAvatar ?: "No avatar"})"
                )
            }
            Log.e("Watchlist", "------------------------------------")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        lifecycleScope.launch {
            val client = NetworkModule.provideHttpClient()
            val movieApi = NetworkModule.providePostApi(client = client)
            printAllMovies(api = movieApi, userId = "698c72b29416d629588ce5b3")
        }
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}