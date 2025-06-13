package org.example.project.cat.presentation

import org.example.project.cat.domain.Cat

interface CatListAction {
    data class OnSearchQuery(val searchQuery: String) : CatListAction
    data class OnCatClick(val cat: Cat) : CatListAction
}