package com.assignment.search.presentations

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assignment.core.bases.BaseScreen
import com.assignment.theme.component.SearchBarView
import com.assignment.theme.extensions.WindowSizeClass
import com.assignment.theme.component.ScaffoldTopAppbar
import com.assignment.theme.extensions.getWindowSizeClass
import com.assignment.theme.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onBackBtnClick: (OnBackPressedDispatcher?) -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    LaunchedEffect(Unit) {
        viewModel.getAllProducts()
    }

    BaseScreen(
        baseViewState = state,
        content = {
            SearchComponent(
                viewModel = viewModel,
                onBackBtnClick = {
                    onBackBtnClick.invoke(backDispatcher)
                }
            )
        },
    )
}


@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    onBackBtnClick: () -> Unit

) {

    val isTablet = getWindowSizeClass() == WindowSizeClass.Expanded

    val spanCount = if (isTablet) 4 else 2

    var search = viewModel.searchQuery.value
    val filteredProducts by viewModel.filteredProducts

    ScaffoldTopAppbar(
        title = "Search",
        onNavigationIconClick = onBackBtnClick
    ) { innerPadding ->

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

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
                        query = search,
                        onQueryChange = { query ->
                            search = query
                            viewModel.setSearchQuery(query)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }


                itemsIndexed(filteredProducts) { index, product ->
                    Box(
                        modifier = Modifier.padding(AppTheme.dimens.paddingExtraSmall)
                    ) {
                        ProductCard(product = product)
                    }
                }

            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        onBackBtnClick = {}
    )
}
