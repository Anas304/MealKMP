package org.example.project.meal.presentation.meal_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.meal.presentation.meal_detail.components.BlurredImageBackground
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MealDetailScreenRoot(
    viewModel: MealDetailsViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MealDetailScreen(
        state = state,
        onAction = { action ->
            when(action){
                MealDetailsAction.OnBackClick -> {
                    onBackClick()
                }
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun MealDetailScreen(
    state: MealDetailState,
    onAction: (MealDetailsAction) -> Unit,
) {
    BlurredImageBackground(
        imageUrl = state.meal?.imageUrl,
        isFavorite = state.isFavorite,
        onFavoriteClick = {
            onAction(MealDetailsAction.OnFavoriteMealClick)
        },
        onBackClick = {
            onAction(MealDetailsAction.OnBackClick)
        },
        modifier = Modifier
            .fillMaxSize()
    ){

    }
}