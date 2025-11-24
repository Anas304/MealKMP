package org.example.project.meal.presentation.meal_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

context(viewModel : ViewModel)
fun <T> Flow<T>.stateInWhileSubscribed(initialState: T) : StateFlow<T> {
    return this.stateIn(
        scope = viewModel.viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = initialState
    )
}