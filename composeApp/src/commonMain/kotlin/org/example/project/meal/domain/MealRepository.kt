package org.example.project.meal.domain

import kotlinx.coroutines.flow.Flow
import org.example.core.domain.DataError
import org.example.core.domain.Result

interface MealRepository {
    suspend fun searchMeals(query: String): Result<List<Meal>, DataError.Remote>

    fun getFavoriteMeals(): Flow<List<Meal>>
    fun isFavoriteMeal(mealId: String): Flow<Boolean>
    suspend fun markAsFavorite(meal: Meal)
    suspend fun deleteFromFavorite(meal: Meal)
}