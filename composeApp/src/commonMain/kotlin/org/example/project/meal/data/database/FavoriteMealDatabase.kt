package org.example.project.meal.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MealEntity::class],
    version = 1
)
@ConstructedBy(MealDatabaseConstructor::class)
abstract class FavoriteMealDatabase : RoomDatabase(){

    abstract val favoriteMealDao: FavoriteMealDao

    companion object {
        const val DATABASE_NAME = "meal_db"
    }

}