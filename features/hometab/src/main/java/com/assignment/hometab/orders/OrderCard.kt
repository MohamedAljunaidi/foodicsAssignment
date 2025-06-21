package com.assignment.hometab.orders

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.assignment.hometab.tables.domain.model.Order
import com.assignment.theme.theme.AppTheme
import com.assignment.theme.theme.Shapes
import java.util.Locale


@Composable
fun OrderCard(
    order: Order,
    onDeleteClick: ((order: Order) -> Unit)? = null
) {
    Card(
        shape = Shapes.large,
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = AppTheme.dimens.paddingSmall,
                vertical = AppTheme.dimens.paddingExtraSmall
            )
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.padding(AppTheme.dimens.paddingSmall)) {
                AsyncImage(
                    model = order.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(AppTheme.dimens.imageSizeMedium)
                        .clip(Shapes.medium)
                )

                Spacer(modifier = Modifier.width(AppTheme.dimens.paddingSmall))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = order.name ?: "",
                        color = AppTheme.colors.black,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = AppTheme.dimens.titleMedium
                        ),

                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = order.description ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "$${String.format(Locale.US, "%.2f", order.price)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Delete",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(AppTheme.dimens.paddingSmall)
                    .clickable { onDeleteClick?.invoke(order) },
                tint = Color.Red
            )
        }
    }
}


@Preview(name = "Phone - Small (≤360dp)", widthDp = 360, heightDp = 640)
@Composable
fun FoodGridSmallPreview() {

    OrderCard(
        Order(
            name = "Bacon & Cheese Burger",
            description = "Bacon & Cheese Burger",
            image = "https://dummyimage.com/300.png/09f/fff",
            price = 10.99,
        )
    )
}

@Preview(name = "Phone - Normal (361–599dp)", widthDp = 411, heightDp = 740)
@Composable
fun FoodGridNormalPreview() {

    OrderCard(
        Order(
            name = "Bacon & Cheese Burger",
            description = "Bacon & Cheese Burger",
            image = "https://dummyimage.com/300.png/09f/fff",
            price = 10.99,
        )
    )
}

@Preview(name = "Tablet (≥600dp)", widthDp = 800, heightDp = 1280)
@Composable
fun FoodGridTabletPreview() {
    OrderCard(
        Order(
            name = "Bacon & Cheese Burger",
            description = "Bacon & Cheese Burger",
            image = "https://dummyimage.com/300.png/09f/fff",
            price = 10.99,
        )
    )
}

