package org.example.project.movie.presentation.movie_list

import org.example.project.movie.domain.Movie

sealed interface MovieListAction {
    data class OnSearchQuery(val query: String) : MovieListAction
    data class OnMovieClick(val movie: Movie) : MovieListAction
    data class OnTabSelected(val index : Int) : MovieListAction
}