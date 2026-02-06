package de.cinelog.network

import kotlinx.serialization.Serializable

@Serializable
data class Movie(val uuid: String, val title: String, val year: Int)

