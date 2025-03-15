package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.example.project.movie.presentation.movie_list.MovieListViewModel
import org.example.project.movie.presentation.movie_list.MovieScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = remember { MovieListViewModel() }
        MovieScreenRoot(
            viewModel = viewModel,
            onMovieClick = {

            }
        )
    }
}