package de.travelinski

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform