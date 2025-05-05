package org.example.project.meal.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MealEntity(
    @PrimaryKey(autoGenerate = false)  val id: String,
    val title: String,
    val category: String,
    val area: String,
    val imageUrl: String,
    val instructions: String?
)
