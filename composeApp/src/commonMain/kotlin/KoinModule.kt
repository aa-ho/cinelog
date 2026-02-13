import de.cinelog.MainViewModel
import de.cinelog.network.MovieApi
import de.cinelog.network.MovieApiImpl
import de.cinelog.network.NetworkModule
import io.ktor.client.HttpClient
import org.koin.dsl.module

val appModule = module {
    single { NetworkModule.provideHttpClient() }
    single<MovieApi> { NetworkModule.providePostApi(get()) }
    factory { MainViewModel(get()) }
}
