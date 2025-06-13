package org.example.project.cat.presentation

import org.example.core.presentation.UiText
import org.example.project.cat.domain.Cat

data class CatListState(
    val searchQuery: String? = "Ragdoll",
    val searchResult:List<Cat> = emptyList(),
    val isLoading: Boolean = false,
    val catList: List<Cat> = emptyList(),
    val errorMessage: UiText? = null
)
