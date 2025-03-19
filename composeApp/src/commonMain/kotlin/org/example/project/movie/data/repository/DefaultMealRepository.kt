package org.example.project.movie.data.repository

import org.example.core.domain.DataError
import org.example.core.domain.Result
import org.example.project.movie.data.network.RemoteMealDataSource
import org.example.project.movie.domain.Meal

class DefaultMealRepository(
    private val remoteMealDataSource: RemoteMealDataSource
) {

    //Offline-First

    suspend fun searchMeals(query: String) : Result<List<Meal>, DataError.Remote>{
        return remoteMealDataSource.searchMeals(query)
    }


}