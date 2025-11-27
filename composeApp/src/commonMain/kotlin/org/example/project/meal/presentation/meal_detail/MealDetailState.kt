package org.example.project.meal.presentation.meal_detail

import org.example.project.meal.domain.Meal

data class MealDetailState(
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val meal: Meal? = null,
)
