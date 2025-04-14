package org.example.project.meal.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.meal.domain.Meal

class SelectedMealViewModel : ViewModel() {

    private val _selectedMeal = MutableStateFlow<Meal?>(null)
    val selectedMeal = _selectedMeal.asStateFlow()

    fun onMealSelected(meal: Meal) {
        _selectedMeal.value = meal
    }
}