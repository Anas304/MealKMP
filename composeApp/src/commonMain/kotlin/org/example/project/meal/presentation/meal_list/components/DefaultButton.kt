package org.example.project.meal.presentation.meal_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun DefaultButton(
    icon: DrawableResource? = null,
    iconSize: Dp = 24.dp,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    textColor: Color = MaterialTheme.colorScheme.background,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    borderColor: Color = Color.Transparent,
    onClick: () -> Unit = { },
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.heightIn(min = 58.dp),
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        contentPadding = ButtonDefaults.ContentPadding,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(
                6.dp,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let { painterResource(resource = it) }?.let {
                Icon(
                    modifier = Modifier
                        .size(iconSize),
                    painter = it,
                    tint = textColor,
                    contentDescription = null
                )
            }
            Text(
                text = text,
                style = textStyle,
                textAlign = TextAlign.Center,
                color = textColor,
            )
        }
    }
}