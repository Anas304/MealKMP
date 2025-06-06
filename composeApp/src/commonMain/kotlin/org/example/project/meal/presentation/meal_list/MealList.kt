package org.example.project.meal.presentation.meal_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.meal.domain.Meal
import org.example.project.meal.presentation.meal_list.components.MealListItem

@Composable
fun MealList(
    meal: List<Meal>,
    onMovieClick: (Meal) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            items = meal,
            key = { it.id }
        ) { movie ->
            MealListItem(
                meal = movie,
                onClick = {
                    onMovieClick(movie)
                },
                modifier = modifier
                    .widthIn(max = 700.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

        }
    }
}