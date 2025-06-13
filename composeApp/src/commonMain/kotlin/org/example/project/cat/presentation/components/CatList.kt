package org.example.project.cat.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.cat.domain.Cat

@Composable
fun CatList(
    modifier: Modifier = Modifier,
    cat: List<Cat>,
    onCatClick: (Cat) -> Unit,
    scrollState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            items = cat,
            key = { it.id }
        ) { cat ->
            CatListItem(
                cat = cat,
                onCatClick = {
                    onCatClick(cat)
                },
                modifier = modifier
                    .widthIn(max = 700.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}