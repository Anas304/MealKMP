package org.example.project.movie.presentation.movie_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.bookpedia.core.presentation.DarkBlue
import com.plcoding.bookpedia.core.presentation.DesertWhite
import com.plcoding.bookpedia.core.presentation.SandYellow
import moviekmp.composeapp.generated.resources.Res
import moviekmp.composeapp.generated.resources.search_results
import org.example.project.movie.domain.Movie
import org.example.project.movie.presentation.movie_list.components.BookSearchBar
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieScreenRoot(
    viewModel: MovieListViewModel = koinViewModel(),
    onMovieClick: (Movie) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    MovieListScreen(
        state = state.value,
        onAction = { action ->
            when (action){
                is MovieListAction.OnMovieClick -> {
                    onMovieClick(action.movie)
                }
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun MovieListScreen(
    state: MovieListState,
    onAction: (MovieListAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val pagerState = rememberPagerState { 2 }

    val searchResultListState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBlue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = { searcQuery ->
                onAction(MovieListAction.OnSearchQuery(searcQuery))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(max = 700.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = DesertWhite,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            TabRow(
                selectedTabIndex = state.selectedTabIndex,
                modifier = Modifier
                    .widthIn(max = 700.dp)
                    .fillMaxWidth(),
                containerColor = DesertWhite,
                indicator = { tabPositions->
                    TabRowDefaults.SecondaryIndicator(
                        color = SandYellow,
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[state.selectedTabIndex])
                    )
                }
            ){
                Tab(
                    selected = state.selectedTabIndex == 0,
                    onClick = {
                        onAction(MovieListAction.OnTabSelected(0))
                    },
                    selectedContentColor = SandYellow,
                    unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                ) {
                    Text(
                        text = stringResource(Res.string.search_results),
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                    )
                }
                Tab(
                    selected = state.selectedTabIndex == 1,
                    onClick = {
                        onAction(MovieListAction.OnTabSelected(1))
                    },
                    selectedContentColor = SandYellow,
                    unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                ) {
                    Text(
                        text = stringResource(Res.string.search_results),
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { pageIndex ->
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    when(pageIndex){
                       0 -> {
                           if (state.isLoading){
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

                                   state.searchResult.isEmpty() -> {
                                       Text(
                                           text = stringResource(Res.string.search_results),
                                           textAlign = TextAlign.Center,
                                           style = MaterialTheme.typography.headlineSmall,
                                           color = MaterialTheme.colorScheme.background
                                       )
                                   }

                                   else -> {
                                       MovieList(
                                           movies = state.searchResult,
                                           onMovieClick = { clickedMovie ->
                                               onAction(MovieListAction.OnMovieClick(clickedMovie))
                                           },
                                           scrollState = searchResultListState
                                       )
                                   }
                               }
                           }
                       }

                        1 -> {

                        }
                    }
                }
            }
        }
    }
}