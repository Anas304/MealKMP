package org.example.project.meal.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MealEntity::class],
    version = 1
)
abstract class FavoriteMealDatabase : RoomDatabase(){

    abstract val dao: FavoriteMealDao

    companion object {
        const val DATABASE_NAME = "meal_db"
    }

}