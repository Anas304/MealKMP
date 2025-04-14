package org.example.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.example.project.meal.presentation.SelectedMealViewModel
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
                    val selectedMealViewModel = it.sharedKoinViewModel<SelectedMealViewModel>(navController)
                    MealScreenRoot(
                        viewModel = viewModel,
                        onMealClick = { meal ->
                            selectedMealViewModel.onMealSelected(meal)
                            navController.navigate(Route.MealDetail(meal.id))
                        }
                    )
                }
            }

            composable<Route.MealDetail> {
                val selectedMealViewModel = it.sharedKoinViewModel<SelectedMealViewModel>(navController)
                val selectedMeal by selectedMealViewModel.selectedMeal.collectAsStateWithLifecycle()
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Meal Detail screen's ID is\n$selectedMeal")
                }
            }
        }
    }
}
@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraph = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraph)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}