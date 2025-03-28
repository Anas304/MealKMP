package org.example.project.meal.domain

data class Meal(
    val id : Int,
    val title: String,
    val category: String,
    val area: String,
    val instructions: String,
    val imageUrl: String,
   // val tags: String?,
    //val ingredients: List<String>?,
)
