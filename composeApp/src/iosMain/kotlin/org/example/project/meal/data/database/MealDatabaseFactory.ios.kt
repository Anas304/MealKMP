@file:OptIn(ExperimentalForeignApi::class)

package org.example.project.meal.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class MealDatabaseFactory {
    actual fun create(): RoomDatabase.Builder<FavoriteMealDatabase> {
        val dbFile = documentDirectory() + "/${FavoriteMealDatabase.DATABASE_NAME}"
        return Room.databaseBuilder<FavoriteMealDatabase>(
            name = dbFile
        )
    }

    private fun documentDirectory() : String{
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )
        return requireNotNull(documentDirectory?.path)
    }
}