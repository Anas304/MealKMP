package org.example.project.meal.data.repository

import org.example.core.domain.DataError
import org.example.core.domain.Result
import org.example.core.domain.map
import org.example.project.meal.data.mappers.toMeal
import org.example.project.meal.data.network.RemoteMealDataSource
import org.example.project.meal.domain.Meal
import org.example.project.meal.domain.MealRepository

class DefaultMealRepository(
    private val remoteMealDataSource: RemoteMealDataSource
) : MealRepository {

    //Offline-First

   override suspend fun searchMeals(query: String) : Result<List<Meal>, DataError.Remote>{
        return remoteMealDataSource
            .searchMeals(query)
            .map { dto->
                dto.response?.map { transform->
                    transform.toMeal()
                } ?: emptyList()
            }
    }
}