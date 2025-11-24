package org.example.project.meal.data.database

import androidx.room.RoomDatabase

expect class MealDatabaseFactory {
    fun create() : RoomDatabase.Builder<FavoriteMealDatabase>

}