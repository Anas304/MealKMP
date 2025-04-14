package org.example.project.meal.presentation.meal_detail

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MealDetailsViewModel {

    private val _mealDetails = MutableStateFlow<MealDetailState?>(null)
    val mealDetails = _mealDetails.asStateFlow()
}