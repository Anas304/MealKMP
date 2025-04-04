package org.example.project.meal.presentation.meal_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.core.domain.onError
import org.example.core.domain.onSuccess
import org.example.core.presentation.toUiText
import org.example.project.meal.domain.Meal
import org.example.project.meal.domain.MealRepository

class MealListViewModel(
    private val repository: MealRepository
) : ViewModel() {
    val cachedMeals = emptyList<Meal>()
    var searchJob : Job? = null
    private val _uiState= MutableStateFlow(MealListState())
    val uiState = _uiState
        .onStart {
            if (cachedMeals.isEmpty()){
                observeSearchQuery()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _uiState.value
        )

    fun onAction(action: MealListAction) {
        when (action) {
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

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        _uiState
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isNullOrBlank() -> _uiState.update {
                        it.copy(
                            isLoading = false,
                            searchResult = cachedMeals
                        )
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchMeal(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchMeal(query: String) = viewModelScope.launch {

        _uiState.update {
            it.copy(isLoading = true)
        }

        repository
            .searchMeals(query)
            .onSuccess {searchResult ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        searchResult = searchResult
                    )
                }
            }
            .onError {error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        searchResult = emptyList(),
                        errorMessage = error.toUiText(),
                    )

                }
            }
    }
}