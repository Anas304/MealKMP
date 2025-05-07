package org.example.project.meal.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class MealDatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<FavoriteMealDatabase> {
       val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(FavoriteMealDatabase.DATABASE_NAME)
        return Room.databaseBuilder(
            appContext,
            FavoriteMealDatabase::class.java,
            dbFile.absolutePath
        )
    }
}