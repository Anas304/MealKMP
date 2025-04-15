package org.example.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.example.project.meal.presentation.SelectedMealViewModel
import org.example.project.meal.presentation.meal_detail.MealDetailScreenRoot
import org.example.project.meal.presentation.meal_detail.MealDetailsAction
import org.example.project.meal.presentation.meal_detail.MealDetailsViewModel
import org.example.project.meal.presentation.meal_list.MealListViewModel
import org.example.project.meal.presentation.meal_list.MealScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    MaterialTheme {
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
                        val selectedMealViewModel =
                            it.sharedKoinViewModel<SelectedMealViewModel>(navController)

                        LaunchedEffect(true){
                            selectedMealViewModel.onMealSelected(null)
                        }

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
                    val selectedMealViewModel =
                        it.sharedKoinViewModel<SelectedMealViewModel>(navController)
                    val viewModel = koinViewModel<MealDetailsViewModel>()
                    val selectedMeal by selectedMealViewModel.selectedMeal.collectAsStateWithLifecycle()

                    LaunchedEffect(selectedMeal){
                        selectedMeal?.let {
                            viewModel.onAction(MealDetailsAction.OnSelectedMealChange(it))
                        }
                    }

                    MealDetailScreenRoot(
                        viewModel = viewModel,
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
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