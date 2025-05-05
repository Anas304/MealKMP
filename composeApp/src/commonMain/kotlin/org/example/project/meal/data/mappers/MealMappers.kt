package org.example.project.meal.data.mappers

import org.example.project.meal.data.database.MealEntity
import org.example.project.meal.data.dto.SearchedMealDto
import org.example.project.meal.domain.Meal

fun SearchedMealDto.toMeal() : Meal {
    return Meal(
        id = id.substringAfterLast("/"),
        title = mealName,
        category = category,
        area = area,
        instructions = instructions,
        imageUrl = mealThumb,
    )
}

fun Meal.toMealEntity() : MealEntity {
    return MealEntity(
        id = id,
        title = title,
        category = category,
        area = area,
        imageUrl = imageUrl,
        instructions = instructions
    )
}