package org.example.project.movie.presentation.meal_list

import org.example.core.presentation.UiText
import org.example.project.movie.domain.Meal

data class MovieListState(
    val searchQuery: String = "Avatar",
    val searchResult: List<Meal> = movies,
    val favoriteMovie: List<Meal> = emptyList(),
    val selectedTabIndex: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null
)

val movies = (1..20).map {
    Meal(
        id = it,
        imageUrl = "https//:unknown.com",
        title = "Movie $it",
        description = "This movie is about $it % people to watch",
        rating = 4.55656,
    )
}
