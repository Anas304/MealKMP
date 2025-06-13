package org.example.project.cat.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.cat.domain.Cat
import org.example.project.cat.presentation.components.CatList
import org.example.project.meal.presentation.meal_list.components.AppSearchBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CatListRoot(
    viewModel: CatListViewModel = koinViewModel(),
    onCatClick: (Cat) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    CatListScreenContent(
        state = state.value,
        onAction = { action ->
            when(action){
                is CatListAction.OnCatClick -> {
                    onCatClick(action.cat)
                }
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}



@Composable
fun CatListScreenContent(
    modifier: Modifier = Modifier,
    state: CatListState,
    onAction: (CatListAction) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AppSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = { searchQuery ->
                onAction(CatListAction.OnSearchQuery(searchQuery))
            },
            onImeSearch = {
                keyboardController?.hide()
            }
        )
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            when{
                state.errorMessage != null -> {
                    Text(
                        text = state.errorMessage.asString(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                else -> {
                    CatList(
                        cat = state.catList,
                        onCatClick = { clickedCat ->
                            onAction(CatListAction.OnCatClick(clickedCat))
                        }
                    )
                }
            }
        }
    }
}