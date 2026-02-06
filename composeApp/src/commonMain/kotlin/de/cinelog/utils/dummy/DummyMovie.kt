package de.cinelog.utils.dummy

import de.cinelog.data.Director
import de.cinelog.data.MovieData
import kotlinx.datetime.LocalDate
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

fun dummyMovies() = buildList {
    add(dummyMovie())
    add(dummyMovie2())
    add(dummyMovie3())
    add(dummyMovie4())
    add(dummyMovie5())
}

fun dummyMovie() = MovieData(
    uuid = "1a",
    title = "The Odyssey",
    iMDbUrl = "https://www.imdb.com/title/tt33764258/?ref_=nv_sr_srsg_0_tt_8_nm_0_in_0_q_odys",
    rottenTomatoesUrl = "https://www.rottentomatoes.com/m/the_odyssey_2026",
    duration = 5.hours + 30.minutes,
    releaseDate = LocalDate(2026, 7, 21),
    director = Director(name = "Christopher Nolan"),
    coverUrl = "https://dx35vtwkllhj9.cloudfront.net/universalstudios/the-odyssey/images/regions/us/onesheet.jpg",
)

fun dummyMovie2() = MovieData(
    uuid = "1b",
    title = "Send Help",
    iMDbUrl = "https://www.imdb.com/title/tt8036976/", // real IMDb
    rottenTomatoesUrl = "https://www.rottentomatoes.com/m/send_help", // RT
    duration = 1.hours + 55.minutes,
    releaseDate = LocalDate(2026, 1, 30),
    director = Director(name = "Sam Raimi"),
    coverUrl = "https://m.media-amazon.com/images/M/MV5BMjZlY2NiYzYtMDVkNC00OTE4LWE5NGItYjJkNTEyMDAzZjg1XkEyXkFqcGc@._V1_.jpg", // approximate poster URL
)

fun dummyMovie3() = MovieData(
    uuid = "1c",
    title = "Pillion",
    iMDbUrl = "https://www.imdb.com/title/tt32321317/", // real IMDb
    rottenTomatoesUrl = "https://www.rottentomatoes.com/m/pillion", // RT
    duration = 1.hours + 46.minutes,
    releaseDate = LocalDate(2026, 2, 6),
    director = Director(name = "Harry Lighton"),
    coverUrl = "https://images.fandango.com/ImageRenderer/500/0/redesign/static/img/default_poster--dark-mode.png/0/images/masterrepository/Fandango/242722/1Sheet_V5_Pillion.jpg",
)

fun dummyMovie4() = MovieData(
    uuid = "1d",
    title = "Wuthering Heights",
    iMDbUrl = "https://www.imdb.com/title/tt15799728/", // real IMDb (likely)
    rottenTomatoesUrl = "https://www.rottentomatoes.com/m/wuthering_heights_2026", // guessed slug
    duration = 2.hours + 16.minutes,
    releaseDate = LocalDate(2026, 2, 13),
    director = Director(name = "Emerald Fennell"),
    coverUrl = "https://resizing.flixster.com/rWy-5l9uYvZSca4NHzNKzUexReA=/206x305/v2/https://resizing.flixster.com/P18b8DE1oAj8wlSlXG3YI6FMNwo=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzL2QyOWRhZDNlLTU4MmUtNGJhYy05NzJhLTk5OGVlMWNmNTg4MS5qcGc=",
)

fun dummyMovie5() = MovieData(
    uuid = "1e",
    title = "The Super Mario Galaxy Movie",
    iMDbUrl = "https://www.imdb.com/title/tt28650488/",
    rottenTomatoesUrl = "https://www.rottentomatoes.com/m/the_super_mario_galaxy_movie", // RT
    duration = 1.hours + 45.minutes,
    releaseDate = LocalDate(2026, 4, 3),
    director = Director(name = "Aaron Horvath & Michael Jelenic"),
    coverUrl = "https://www.upig.de/tl_files/content/movies/der-super-mario-galaxy-film/der-super-mario-galaxy-film_plakat.jpg",
)