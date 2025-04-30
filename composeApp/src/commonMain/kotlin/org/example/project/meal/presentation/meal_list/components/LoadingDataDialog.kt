package org.example.project.meal.presentation.meal_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import moviekmp.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoadingDataDialog(
    title: String,
    message: String? = null,
    annotatedMessage: AnnotatedString? = null,
    dismissOnClickOutside: Boolean = true,
    buttonText: String,
    contentColor: Color = MaterialTheme.colorScheme.error,
    onButtonClick: () -> Unit,
    onDismiss: () -> Unit
) {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            Res.readBytes("files/loading_data.json").decodeToString()
        )
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = dismissOnClickOutside
        ),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f),
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberLottiePainter(
                        composition = composition,
                        iterations = Compottie.IterateForever
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .widthIn(min = 163.dp)
                        .heightIn(min = 185.dp)
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 20.sp,
                    ),
                    color = contentColor,
                )
                annotatedMessage?.let {
                    BasicText(
                        annotatedMessage,
                        modifier = Modifier.padding(horizontal = 30.dp),
                        style = MaterialTheme.typography.displaySmall.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                        ),
                    )
                } ?: Text(
                    modifier = Modifier
                        .padding(horizontal = 30.dp),
                    text = message ?: "",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontSize = 18.sp,
                    ),
                )
                DefaultButton(
                    text = buttonText,
                    containerColor = contentColor,
                    onClick = onButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .padding(horizontal = 10.dp),
                )

            }
        }
    }
}