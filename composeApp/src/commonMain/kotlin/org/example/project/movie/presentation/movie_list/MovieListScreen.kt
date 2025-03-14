package org.example.project.movie.presentation.movie_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.project.movie.domain.Movie

@Composable
fun MovieListScreen(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {

}