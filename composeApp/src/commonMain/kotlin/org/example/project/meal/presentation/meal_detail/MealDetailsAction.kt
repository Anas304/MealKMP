package org.example.project.meal.presentation.meal_detail

import org.example.project.meal.domain.Meal

sealed interface MealDetailsAction {

    data object OnBackClick: MealDetailsAction

    data object OnFavoriteMealClick: MealDetailsAction

    data class OnSelectedMealChange(val meal: Meal): MealDetailsAction

}