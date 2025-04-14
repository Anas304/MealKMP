package org.example.project.meal.presentation.meal_detail

import org.example.project.meal.domain.Meal

data class MealDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = true,
    val meal: Meal? = null,
)
