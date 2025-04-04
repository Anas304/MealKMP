package org.example.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.example.project.meal.presentation.meal_list.MealListViewModel
import org.example.project.meal.presentation.meal_list.MealScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.MealGraph
    ) {
        navigation<Route.MealGraph>(
            startDestination = Route.MealList
        ) {
            composable<Route.MealList> {
                MaterialTheme {
                    val viewModel = koinViewModel<MealListViewModel>()
                    MealScreenRoot(
                        viewModel = viewModel,
                        onMealClick = { meal ->
                            navController.navigate(Route.MealDetail(meal.id))
                        }
                    )
                }
            }

            composable<Route.MealDetail> { meal ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Meal Detail screen's ID is ${meal.id}")
                }
            }


        }
    }


}