package org.example.project.meal.data.database

import androidx.room.RoomDatabase

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class MealDatabaseFactory() {
    fun create() : RoomDatabase.Builder<FavoriteMealDatabase>
}