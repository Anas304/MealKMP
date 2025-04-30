package org.example.project.meal.presentation.meal_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import moviekmp.composeapp.generated.resources.Res
import moviekmp.composeapp.generated.resources.description_unavailable
import org.example.project.meal.presentation.meal_detail.components.BlurredImageBackground
import org.jetbrains.compose.resources.stringResource
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
        if (state.meal != null) {
            Column(
                modifier = Modifier
                    .widthIn(max = 700.dp)
                    .fillMaxWidth()
                    .padding(
                        vertical = 16.dp,
                        horizontal = 24.dp
                    )
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.meal.title,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = state.meal.category,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = state.meal.area,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )

                if (state.isLoading) {
                    CircularProgressIndicator()

                } else {
                    Text(
                        text = "Instructions",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .fillMaxWidth()
                            .padding(top = 24.dp, bottom = 8.dp)
                    )
                    Text(
                        text = if (state.meal.instructions.isNullOrBlank()) {
                            stringResource(Res.string.description_unavailable)
                        } else {
                            state.meal.instructions
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (state.meal.instructions.isNullOrBlank()) {
                            Color.Black.copy(alpha = 0.4f)
                        } else {
                            Color.Black
                        },
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }
        
    }
}