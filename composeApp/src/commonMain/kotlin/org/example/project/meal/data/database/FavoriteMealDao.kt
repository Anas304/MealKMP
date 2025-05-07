package org.example.project.meal.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
@Dao
interface FavoriteMealDao {

    @Upsert
    suspend fun upsert(): List<MealEntity>

    @Query("SELECT * FROM MealEntity")
    fun getFavoriteMeals(): Flow<List<MealEntity>>//Flow here will give the latest value whenever we add, delete or update the favorite meal list.

    @Query("SELECT * FROM MealEntity WHERE id = :id")
    suspend fun getFavoriteMeal(id: String): MealEntity?

    @Query("DELETE FROM MealEntity WHERE id = :id")
    suspend fun deleteFavoriteMeal(id: String)

}