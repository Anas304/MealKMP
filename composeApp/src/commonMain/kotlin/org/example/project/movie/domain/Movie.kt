package org.example.project.movie.domain

data class Movie(
    val id : Int,
    val title: String,
    val imageUrl: String,
    //val actors : List<String>,
    val rating: Float?,
)
