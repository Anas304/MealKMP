package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.ktor.client.engine.HttpClientEngine
import org.example.core.data.HttpClientFactory
import org.example.project.meal.data.network.KtorRemoteMealDataSource
import org.example.project.meal.data.repository.DefaultMealRepository
import org.example.project.meal.presentation.meal_list.MealListViewModel
import org.example.project.meal.presentation.meal_list.MealScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    MaterialTheme {
        val viewModel = remember { MealListViewModel(
            repository = DefaultMealRepository(
                remoteMealDataSource = KtorRemoteMealDataSource(
                    httpClient = HttpClientFactory.createHttpClient(engine)
                )
            )
        ) }
        MealScreenRoot(
            viewModel = viewModel,
            onMealClick = {

            }
        )
    }
}