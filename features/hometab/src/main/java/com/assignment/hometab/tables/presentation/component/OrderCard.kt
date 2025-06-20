package com.assignment.hometab.tables.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignment.theme.theme.AppTheme
import com.assignment.theme.theme.Shapes

@Composable
fun OrderCard(
    modifier: Modifier = Modifier,
    orderNumber: String,
    buttonText: String,
    amount: String,
    onClick: (() -> Unit)?=null
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.dimens.paddingSmall)
            .clip(Shapes.medium)
            .clickable { onClick?.invoke() },
        color = AppTheme.colors.primary,
        tonalElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = AppTheme.dimens.paddingMedium, vertical = AppTheme.dimens.paddingSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = orderNumber,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = AppTheme.dimens.bodyMedium
                        ),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(AppTheme.dimens.paddingSmall))

                Text(
                    text = buttonText,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = AppTheme.dimens.bodyMedium
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = amount,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = AppTheme.dimens.bodyMedium
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(AppTheme.dimens.paddingSmall))
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OrderCardPreview() {
    OrderCard(
        modifier = Modifier,
        orderNumber = "123",
        buttonText = "Button Text",
        amount = "$10.99"
    )
}
