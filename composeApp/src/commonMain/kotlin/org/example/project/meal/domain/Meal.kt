package org.example.project.meal.domain

data class Meal(
    val id : String,
    val title: String,
    val category: String,
    val area: String,
    val instructions: String?,
    val imageUrl: String,
)
