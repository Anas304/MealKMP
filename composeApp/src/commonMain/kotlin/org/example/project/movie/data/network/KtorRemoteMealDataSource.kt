package org.example.project.movie.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.core.data.safeCall
import org.example.core.domain.DataError
import org.example.core.domain.Result
import org.example.project.movie.data.dto.MealResponseDto

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

class KtorRemoteMealDataSource (
    val httpClient: HttpClient
) : RemoteMealDataSource{

   override suspend fun searchMeals(query: String) : Result<MealResponseDto, DataError.Remote>{
       return safeCall {
           httpClient.get(
               urlString = "$BASE_URL/search.php"
           ) {
               parameter("s",query)
           }
       }
   }

}