package org.example.project.meal.presentation.meal_list

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
import androidx.compose.runtime.LaunchedEffect
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
import moviekmp.composeapp.generated.resources.favorites
import moviekmp.composeapp.generated.resources.no_favorite_movies
import moviekmp.composeapp.generated.resources.search_results
import org.example.project.meal.domain.Meal
import org.example.project.meal.presentation.meal_list.components.MovieSearchBar
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MealScreenRoot(
    viewModel: MealListViewModel = koinViewModel(),
    onMealClick: (Meal) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    MealListScreen(
        state = state.value,
        onAction = { action ->
            when (action){
                is MealListAction.OnMealClick -> {
                    onMealClick(action.meal)
                }
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun MealListScreen(
    state: MealListState,
    onAction: (MealListAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val pagerState = rememberPagerState { 2 }

    val searchResultListState = rememberLazyListState()
    val favoriteMealListState = rememberLazyListState()

    LaunchedEffect(state.searchResult){
        searchResultListState.animateScrollToItem(0)
    }
    LaunchedEffect(state.selectedTabIndex){
        pagerState.animateScrollToPage(state.selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage){
        pagerState.animateScrollToPage(pagerState.currentPage)
    }
    LaunchedEffect(pagerState.currentPage){
        onAction(MealListAction.OnTabSelected(pagerState.currentPage))
    }
    print("Meals data ${state.searchResult}")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBlue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = { searcQuery ->
                onAction(MealListAction.OnSearchQuery(searcQuery))
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
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                TabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
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
                            onAction(MealListAction.OnTabSelected(0))
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
                            onAction(MealListAction.OnTabSelected(1))
                        },
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.favorites),
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
                                            MealList(
                                                meal = state.searchResult,
                                                onMovieClick = { clickedMovie ->
                                                    onAction(MealListAction.OnMealClick(clickedMovie))
                                                },
                                                scrollState = searchResultListState
                                            )
                                        }
                                    }
                                }
                            }

                            1 -> {
                                if (state.favoriteMeal.isEmpty()){
                                    Text(
                                        text = stringResource(Res.string.no_favorite_movies),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = MaterialTheme.colorScheme.background
                                    )
                                } else{
                                    MealList(
                                        meal = state.searchResult,
                                        onMovieClick = { clickedMovie ->
                                            onAction(MealListAction.OnMealClick(clickedMovie))
                                        },
                                        scrollState = searchResultListState
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}