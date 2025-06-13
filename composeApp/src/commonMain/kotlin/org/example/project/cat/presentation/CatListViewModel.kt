package org.example.project.cat.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CatListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CatListState())
    val uiState = _uiState.asStateFlow()


    fun onAction(action: CatListAction) {
        when(action){
            is CatListAction.OnCatClick -> {
                // TODO: cat item click
            }
            is CatListAction.OnSearchQuery -> {
                _uiState.update {
                    it.copy(searchQuery = action.searchQuery)
                }
            }
        }
    }
}