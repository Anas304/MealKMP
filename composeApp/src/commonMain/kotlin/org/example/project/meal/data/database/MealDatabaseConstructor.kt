package org.example.project.meal.data.database

import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MealDatabaseConstructor : RoomDatabaseConstructor<FavoriteMealDatabase>{
    override fun initialize(): FavoriteMealDatabase
}