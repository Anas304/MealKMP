package org.example.project.meal.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealResponseDto(
    @SerialName("meals") val response: List<SearchedMealDto>
)