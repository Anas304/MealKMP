package org.example.project.movie.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedMealDto(
    @SerialName("idMeal") val id : Int,
    @SerialName("strMeal") val mealName : String,
    @SerialName("strCategory") val category : String,
    @SerialName("strArea") val area : String,
    @SerialName("strInstructions") val instructions : String,
    @SerialName("strMealThumb") val mealThumb : String,
)
