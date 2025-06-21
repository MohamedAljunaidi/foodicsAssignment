package com.assignment.hometab.tables.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assignment.core.bases.BaseScreen
import com.assignment.core.bases.BaseViewState
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.hometab.tables.domain.model.Products
import com.assignment.hometab.tables.presentation.component.CategoryTabs
import com.assignment.hometab.tables.presentation.component.AddToCard
import com.assignment.hometab.tables.presentation.component.ProductCard
import com.assignment.navigation.direction.search.SearchDestinationEnum
import com.assignment.navigation.direction.search.SearchNavigator
import com.assignment.navigation.extension.navigateToDirection
import com.assignment.theme.WindowSizeClass
import com.assignment.theme.component.SearchBarView
import com.assignment.theme.getWindowSizeClass
import com.assignment.theme.theme.AppTheme
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
internal fun TablesScreen(
    modifier: Modifier = Modifier,
    viewModel: TablesViewModel = koinViewModel(),
) {

    val categoriesState by viewModel.categories.collectAsStateWithLifecycle()
    val productsState by viewModel.products.collectAsStateWithLifecycle()
    val order by viewModel.orders.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()

    BaseScreen(
        baseViewState = state,
        content = {
            viewModel.getOrders()
            Box(modifier = modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = AppTheme.dimens.paddingMedium)
                ) {
                    TableListScreen(
                        modifier = Modifier.weight(1f),
                        viewModel = viewModel,
                        state = state,
                        categoriesState = categoriesState,
                        productsState = productsState
                    )

                    AddToCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(AppTheme.dimens.paddingMedium),
                        orderNumber = order?.first ?: "0",
                        buttonText = "View Order",
                        amount = "$${String.format(Locale.US, "%.2f", order?.second)}",
                        onClick = {
                            viewModel.deleteOrders()
                        }
                    )
                }

            }
        },
    )
}


@Composable
fun TableListScreen(
    modifier: Modifier = Modifier,
    viewModel: TablesViewModel,
    state: BaseViewState,
    categoriesState: List<Categories>?,
    productsState: List<Products>?
) {
    val context = LocalContext.current
    val isTablet = getWindowSizeClass() == WindowSizeClass.Expanded

    val spanCount = if (isTablet) 4 else 2


    Box(modifier = modifier.fillMaxSize()) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(spanCount),
            contentPadding = PaddingValues(
                start = AppTheme.dimens.paddingSmall,
                end = AppTheme.dimens.paddingSmall,
                top = 0.dp,
                bottom = AppTheme.dimens.paddingSmall
            ),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.paddingSmall),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.paddingSmall),
        ) {
            item(span = { GridItemSpan(spanCount) }) {
                SearchBarView(
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = false,
                    onQueryChange = {},
                    onSearchClick = {
                        SearchNavigator.navigateToDirection(
                            context,
                            destination = SearchDestinationEnum.SEARCH
                        )
                    }
                )

            }


            item(span = { GridItemSpan(spanCount) }) {
                CategoryTabs(
                    categories = categoriesState,
                    onTabSelected = { id ->
                        viewModel.getProductsByCategoryId(id, showLoading = true)
                    }
                )
            }

            item(span = { GridItemSpan(spanCount) }) {
                Spacer(modifier = Modifier.height(AppTheme.dimens.paddingSmall))
            }

            if (state is BaseViewState.ShowOverLayLoading) {
                item(span = { GridItemSpan(spanCount) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                    Spacer(modifier = Modifier.height(AppTheme.dimens.paddingSmall))
                }

            }

            itemsIndexed(productsState.orEmpty()) { index, product ->
                Box(
                    modifier = Modifier.padding(AppTheme.dimens.paddingExtraSmall)
                ) {
                    ProductCard(product = product, onProductClick = {
                        viewModel.insertOrder(product)
                    })
                }
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun TableListScreenPreview() {
    TablesScreen()
}
