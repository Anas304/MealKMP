package org.example.project.meal.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object StringListTypeConverter {


    /**
     * Converts a List<String> into a JSON-formatted string.
     * Used by Room to store the list as a single string in the database.
     *
     * for example....  data class DataClassName(
     * val languages:List<String>, -->
     * val authors:List<String> -->  These needs to be serialized into a form that can be stored in the database.
     * )
     */
    @TypeConverter
    fun fromList(list:List<String>): String {
        return Json.encodeToString(list)
    }

    /**
     * Converts a JSON-formatted string into a List<String>.
     * Used by Room to convert the string stored in the database
     * back into a list when reading from the database.
     */
    @TypeConverter
    fun fromString(value: String): List<String> {
     return Json.decodeFromString(value)
    }

}