package org.example.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MealGraph: Route

    @Serializable
    data object MealList : Route

    @Serializable
    data class MealDetail(val id:String) : Route


}