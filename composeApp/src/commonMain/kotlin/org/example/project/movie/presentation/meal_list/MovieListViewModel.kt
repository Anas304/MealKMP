package org.example.project.movie.presentation.meal_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieListViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<MovieListState> =
        MutableStateFlow(MovieListState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: MealListAction){
        when(action){
            is MealListAction.OnMealClick -> {

            }
            is MealListAction.OnSearchQuery -> {
                _uiState.update {
                    it.copy(searchQuery = action.query)
                }
            }
            is MealListAction.OnTabSelected -> {
                _uiState.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }
        }
    }

}