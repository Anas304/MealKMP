package org.example.project.cat.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import moviekmp.composeapp.generated.resources.Res
import moviekmp.composeapp.generated.resources.book_error_2
import org.example.project.cat.domain.Cat
import org.jetbrains.compose.resources.painterResource

@Composable
fun CatListItem(
    cat: Cat,
    onCatClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }
    val painter = rememberAsyncImagePainter(
        model = cat.imageUrl,
        onSuccess = {
            imageLoadResult =
                if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                    Result.success(it.painter)
                } else {
                    Result.failure(Exception("Invalid image size"))
                }
        },
        onError = {
            it.result.throwable.printStackTrace()
            imageLoadResult = Result.failure(it.result.throwable)
        }
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCatClick() },
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        when (val result = imageLoadResult) {
            null -> CircularProgressIndicator()
            else -> {
                Image(
                    painter = if (result.isSuccess) painter else painterResource(Res.drawable.book_error_2),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .wrapContentSize()
                )
            }

        }
    }
    Text(text = cat.name, style = MaterialTheme.typography.headlineMedium)
    Text(text = cat.description, style = MaterialTheme.typography.titleMedium)
}