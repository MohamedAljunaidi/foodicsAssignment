package com.assignment.search.presentations

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.assignment.search.domain.model.Products
import com.assignment.theme.theme.AppTheme


@Composable
fun ProductCard(product: Products) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .size(
                width = AppTheme.dimens.cardMinWidth,
                height = AppTheme.dimens.cardMinHeight
            )
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


