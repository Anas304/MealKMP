package org.example.project.meal.data.network

import org.example.core.domain.DataError
import org.example.core.domain.Result
import org.example.project.meal.data.dto.MealResponseDto

interface RemoteMealDataSource {

    suspend fun searchMeals(query: String) : Result<MealResponseDto, DataError.Remote>
}