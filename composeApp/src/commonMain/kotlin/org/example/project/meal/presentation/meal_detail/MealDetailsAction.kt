package org.example.project.meal.presentation.meal_detail

sealed interface MealDetailsAction {

    data object OnMealClick: MealDetailsAction
    data object OnFavoriteMealClick: MealDetailsAction
    data object OnSelectedMealChange: MealDetailsAction
}