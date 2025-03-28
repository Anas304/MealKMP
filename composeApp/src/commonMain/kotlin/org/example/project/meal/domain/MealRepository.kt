package org.example.project.meal.domain

import org.example.core.domain.DataError
import org.example.core.domain.Result

interface MealRepository {
    suspend fun searchMeals(query: String) : Result<List<Meal>, DataError.Remote>
}