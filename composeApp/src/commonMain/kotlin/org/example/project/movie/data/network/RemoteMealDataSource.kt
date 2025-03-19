package org.example.project.movie.data.network

import org.example.core.domain.DataError
import org.example.core.domain.Result
import org.example.project.movie.data.dto.MealResponseDto

interface RemoteMealDataSource {

    suspend fun searchMeals(query: String) : Result<MealResponseDto, DataError.Remote>
}