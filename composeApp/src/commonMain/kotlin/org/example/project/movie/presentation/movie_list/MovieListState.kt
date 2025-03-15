package org.example.project.movie.presentation.movie_list

import org.example.core.presentation.UiText
import org.example.project.movie.domain.Movie

data class MovieListState(
    val searchQuery: String = "Avatar",
    val searchResult: List<Movie> = movies,
    val favoriteMovie: List<Movie> = emptyList(),
    val selectedTabIndex: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null
)

val movies = (1..20).map {
    Movie(
        id = it,
        imageUrl = "https//:unknown.com",
        title = "Movie $it",
        description = "This movie is about $it % people to watch",
        rating = 4.55656,
    )
}
