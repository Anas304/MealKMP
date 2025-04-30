package org.example.project.meal.presentation.meal_list

import org.example.project.meal.domain.Meal

sealed interface MealListAction {
    data class OnSearchQuery(val query: String) : MealListAction
    data class OnMealClick(val meal: Meal) : MealListAction
    data class OnTabSelected(val index: Int) : MealListAction
    data object OnDialogDismiss : MealListAction
}