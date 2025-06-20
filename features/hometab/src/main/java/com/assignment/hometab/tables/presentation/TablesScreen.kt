package com.assignment.hometab.tables.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assignment.core.bases.BaseScreen
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.hometab.tables.domain.model.Products
import com.assignment.hometab.tables.presentation.component.ProductCard
import com.assignment.hometab.tables.presentation.component.CategoryTabs
import com.assignment.hometab.tables.presentation.component.OrderCard
import com.assignment.hometab.tables.presentation.component.SearchView
import com.assignment.theme.WindowSizeClass
import com.assignment.theme.getWindowSizeClass
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun TablesScreen(
    modifier: Modifier = Modifier,
    viewModel: TablesViewModel = koinViewModel(),
) {

    val categoriesState by viewModel.categories.collectAsStateWithLifecycle()
    val productsState by viewModel.products.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()

    BaseScreen(
        baseViewState = state,
        content = {
            categoriesState?.first()
            Box(modifier = modifier.fillMaxSize()) {
                TableListScreen(
                    modifier,
                    categoriesState,
                    productsState
                )
                OrderCard(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    orderNumber = "123",
                    buttonText = "Button Text",
                    amount = "$10.99", {

                    }
                )
            }
        },
    )
}


@Composable
fun TableListScreen(
    modifier: Modifier = Modifier,
    categoriesState: List<Categories>?,
    productsState: List<Products>?
) {

    val isTablet = getWindowSizeClass() == WindowSizeClass.Expanded

    val spanCount = if (isTablet) 4 else 2


    Box(modifier = modifier.fillMaxSize()) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(spanCount),
            contentPadding = PaddingValues(
                start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // Full-width items using Span
            item(span = { GridItemSpan(spanCount) }) {
                SearchView(
                    modifier = Modifier.padding(4.dp)
                )
            }

            item(span = { GridItemSpan(spanCount) }) {
                Spacer(modifier = Modifier.height(10.dp))
            }

            item(span = { GridItemSpan(spanCount) }) {
                CategoryTabs(
                    categories = categoriesState,
                    onTabSelected = { id ->


                    }
                )
            }

            item(span = { GridItemSpan(spanCount) }) {
                Spacer(modifier = Modifier.height(10.dp))
            }

            itemsIndexed(productsState.orEmpty()) { index, product ->
                Box(
                    modifier = Modifier.padding(4.dp)
                ) {
                    ProductCard(product = product, onProductClick = {


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
