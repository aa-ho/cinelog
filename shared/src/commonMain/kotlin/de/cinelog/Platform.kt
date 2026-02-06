package de.cinelog

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform