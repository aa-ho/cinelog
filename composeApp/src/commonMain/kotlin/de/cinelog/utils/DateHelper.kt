package de.cinelog.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Duration

fun formatSmartDate(
    date: LocalDate,
    today: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
): String {
    val daysBetween = today.toEpochDays() - date.toEpochDays()
    return when {
        daysBetween == 0L -> "today"
        daysBetween == 1L -> "yesterday"
        daysBetween in 2..7 -> date.dayOfWeek.name.lowercase()
            .replaceFirstChar { it.uppercase() } // Monday, Tuesday...
        today.year == date.year -> "${date.day}.${date.month.number}" // same year, older than a week
        else -> "${date.year}" // previous year
    }
}

fun formatSmartDateTime(
    dateTime: LocalDateTime,
    now: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
): String {
    val date = dateTime.date
    val today = now.date
    val daysBetween = today.toEpochDays() - date.toEpochDays()
    return when {
        daysBetween == 0L -> {
            val h = dateTime.hour.toString().padStart(2, '0')
            val m = dateTime.minute.toString().padStart(2, '0')
            "$h:$m" // HH:mm
        }

        daysBetween == 1L -> "yesterday"
        daysBetween in 2..7 -> date.dayOfWeek.name.lowercase()
            .replaceFirstChar { it.uppercase() } // Monday, Tuesday...
        today.year == date.year -> "${date.day}.${date.month.number}" // same year, older than a week
        else -> "${date.year}" // previous year
    }
}

fun formatDuration(duration: Duration): String {
    val totalMinutes = duration.inWholeMinutes
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    return when {
        hours > 0 -> if (minutes > 0) "${hours}h ${minutes}m" else "${hours}h"
        else -> "$minutes minutes"
    }
}


fun simpleFormatDate(releaseDate: LocalDate): String {
    return "${releaseDate.day}.${releaseDate.month.number}.${releaseDate.year}"
}

fun LocalDate.daysUntil(other: LocalDate): Int {
    return (other.toEpochDays() - this.toEpochDays()).toInt()
}