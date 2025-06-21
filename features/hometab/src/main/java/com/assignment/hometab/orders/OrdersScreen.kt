package com.assignment.hometab.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assignment.core.bases.BaseScreen
import com.assignment.hometab.tables.domain.model.Order
import com.assignment.theme.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrdersViewModel = koinViewModel(),
) {
    val order by viewModel.orders.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.getOrders()
    BaseScreen(
        baseViewState = state,
        content = {

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = AppTheme.dimens.paddingMedium)
            ) {
                OrderListScreen(
                    viewModel = viewModel,
                    orderState = order
                )
            }
        },
    )
}


@Composable
fun OrderListScreen(
    modifier: Modifier = Modifier,
    viewModel: OrdersViewModel,
    orderState: List<Order>?
) {

    Box(modifier = modifier.fillMaxSize()) {

        LazyColumn(

            contentPadding = PaddingValues(
                start = AppTheme.dimens.paddingSmall,
                end = AppTheme.dimens.paddingSmall,
                top = 0.dp,
                bottom = AppTheme.dimens.paddingSmall
            ),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.paddingExtraSmall),
        ) {

            items(orderState?.size ?: 0) { index ->
                Box {
                    orderState?.get(index)?.let {
                        OrderCard(
                            order = it,
                            onDeleteClick = {
                                viewModel.deleteOrderItem(it)
                            })
                    }
                }
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun TableListScreenPreview() {
    OrdersScreen()
}
