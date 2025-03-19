package org.example.project.movie.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealResponseDto(
    @SerialName("meals") val response: List<SearchedMealDto>
)