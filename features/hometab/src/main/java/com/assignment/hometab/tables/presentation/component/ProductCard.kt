package com.assignment.hometab.tables.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.hometab.tables.domain.model.Products
import com.assignment.theme.theme.AppTheme


@Composable
fun ProductCard(product: Products, onProductClick: ((product: Products) -> Unit)? = null) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .size(
                width = AppTheme.dimens.cardMinWidth,
                height = AppTheme.dimens.cardMinHeight
            )
            .clickable {
                onProductClick?.invoke(product)
            }
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Text(
                text = product.name ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppTheme.dimens.boxMinHeight)
                    .background(
                        color = AppTheme.colors.fieldColor
                    )
                    .padding(
                        start = AppTheme.dimens.paddingSmall,
                        end = AppTheme.dimens.paddingSmall,
                        top = AppTheme.dimens.paddingSmall,
                        bottom = AppTheme.dimens.paddingSmall
                    ),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = AppTheme.dimens.titleMedium,lineHeight = 24.sp
                ),
                color = AppTheme.colors.black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(name = "Phone - Small (≤360dp)", widthDp = 360, heightDp = 640)
@Composable
fun FoodGridSmallPreview() {

    ProductCard(
        Products(
            id = 1,
            name = "Bacon & Cheese Burger",
            image = "https://dummyimage.com/300.png/09f/fff",
            price = 10.99,
            category = Categories(id = 1, name = "Burger"),
            description = "Bacon & Cheese Burger"
        )
    )
}

@Preview(name = "Phone - Normal (361–599dp)", widthDp = 411, heightDp = 740)
@Composable
fun FoodGridNormalPreview() {

    ProductCard(
        Products(
            id = 1,
            name = "Bacon & Cheese Burger",
            image = "https://dummyimage.com/300.png/09f/fff",
            price = 10.99,
            category = Categories(id = 1, name = "Burger"),
            description = "Bacon & Cheese Burger"
        )
    )
}

@Preview(name = "Tablet (≥600dp)", widthDp = 800, heightDp = 1280)
@Composable
fun FoodGridTabletPreview() {
    ProductCard(
        Products(
            id = 1,
            name = "Bacon & Cheese Burger",
            image = "https://dummyimage.com/300.png/09f/fff",
            price = 10.99,
            category = Categories(id = 1, name = "Burger"),
            description = "Bacon & Cheese Burger"
        )
    )
}

