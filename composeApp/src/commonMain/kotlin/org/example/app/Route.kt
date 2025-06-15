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

sealed interface CatRoute {
    @Serializable
    data object CatGraph : CatRoute

    @Serializable
    data object CatList : CatRoute

    @Serializable
    data class CatDetail(val id: String) : CatRoute

}