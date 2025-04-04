package org.example.project.meal.presentation.meal_list

import org.example.core.presentation.UiText
import org.example.project.meal.domain.Meal

data class MealListState(
    val searchQuery: String? = "Biryani",
    val searchResult: List<Meal>? = emptyList(),
    val favoriteMeal: List<Meal> = emptyList(),
    val selectedTabIndex: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null
)

//val meals = (1..20).map {
//    Meal(
//        id = it,
//        imageUrl = "https//:unknown.com",
//        title = "Movie $it",
//        instructions = "This movie is about $it % people to watch",
//        category = "Chicken",
//        area = "Pakistan"
//    )
//}
