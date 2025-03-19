package org.example.project.movie.presentation.meal_list

import org.example.project.movie.domain.Meal

sealed interface MealListAction {
    data class OnSearchQuery(val query: String) : MealListAction
    data class OnMealClick(val movie: Meal) : MealListAction
    data class OnTabSelected(val index : Int) : MealListAction
}