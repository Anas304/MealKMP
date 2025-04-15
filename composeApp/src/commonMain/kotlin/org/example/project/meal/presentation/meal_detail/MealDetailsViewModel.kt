package org.example.project.meal.presentation.meal_detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MealDetailsViewModel : ViewModel() {

    private val _state = MutableStateFlow(MealDetailState())
    val state = _state.asStateFlow()

    fun onAction(action: MealDetailsAction) {
        when (action) {
            MealDetailsAction.OnFavoriteMealClick -> {
               //Add to favorite
            }

            MealDetailsAction.OnBackClick -> {

            }

            is MealDetailsAction.OnSelectedMealChange -> {
                _state.update { prev->
                    prev.copy(meal = action.meal)
                }
            }
        }
    }
}