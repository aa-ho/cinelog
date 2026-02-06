package de.cinelog.utils.dummy

import de.cinelog.data.MediumType
import de.cinelog.data.MovieData
import de.cinelog.data.UserOwningMovie
import de.cinelog.data.UserWatchedAlready
import de.cinelog.data.Watchlist
import de.cinelog.data.WatchlistItem
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

var dummyWatchlist = Watchlist(
    uuid = "45hsac", title = "4er Vision", movies = buildList {
        repeat(1) {
            add(watchListDummyData(dummyMovie()))
            add(watchListDummyData2(dummyMovie2()))
        }
    }, member = listOf(dummyUser1(), dummyUser2(), dummyUser3(), dummyUser4(), dummyUser5())
)
var privateWatchlist = Watchlist(
    uuid = "45hsac", title = "Private", movies = buildList {
        repeat(1) {
            add(watchListDummyData(dummyMovie()))
            add(watchListDummyData2(dummyMovie2()))
            add(watchListDummyData(dummyMovie3()))
            add(watchListDummyData2(dummyMovie4()))
            add(watchListDummyData(dummyMovie5()))
        }
    }, member = listOf(dummyCurrentUser())
)

fun watchListDummyData(movie: MovieData) = WatchlistItem(
    movieData = movie,
    dateAdded = LocalDate(2026, 2, 2),
    lastModified = LocalDateTime(
        date = LocalDate(2026, 2, 4),
        time = LocalTime(hour = 15, minute = 50),
    ),
    addedBy = dummyUser1(),
    wantToWatch = listOf(dummyUser1(), dummyUser2(), dummyUser5(), dummyUser3(), dummyUser4()),
    usersWatchedAlready = listOf(
        UserWatchedAlready(user = dummyUser3(), rating = 3),
        UserWatchedAlready(user = dummyUser1()),
        UserWatchedAlready(user = dummyUser4(), rating = 10),
    ),
    usersOwningMovie = listOf(
        UserOwningMovie(
            user = dummyUser1(), mediumType = MediumType.ULTRA_HD_BLU_RAY,
        ), UserOwningMovie(
            user = dummyUser5(), mediumType = MediumType.ONLINE,
        ), UserOwningMovie(
            user = dummyUser4(), mediumType = MediumType.DVD,
        ), UserOwningMovie(
            user = dummyUser2(), mediumType = MediumType.VHS,
        ), UserOwningMovie(
            user = dummyUser3(), mediumType = MediumType.BLU_RAY,
        )
    ),
    uuid = "3b",
)

fun watchListDummyData2(movie: MovieData) = WatchlistItem(
    movieData = movie,
    dateAdded = LocalDate(2026, 1, 2),
    lastModified = LocalDateTime(
        date = LocalDate(2026, 2, 1),
        time = LocalTime(hour = 8, minute = 24),
    ),
    addedBy = dummyUser3(),
    wantToWatch = listOf(dummyUser1()),
    usersWatchedAlready = listOf(
        UserWatchedAlready(user = dummyUser4(), rating = 10),
    ),
    usersOwningMovie = listOf(
        UserOwningMovie(
            user = dummyUser3(), mediumType = MediumType.BLU_RAY,
        )
    ),
    uuid = "3b",
)