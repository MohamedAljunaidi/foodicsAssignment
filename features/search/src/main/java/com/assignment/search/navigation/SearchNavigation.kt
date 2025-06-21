package com.assignment.search.navigation


import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.assignment.navigation.navigation.constants.NavigationConstants
import com.assignment.search.presentations.SearchScreen

fun NavGraphBuilder.searchNavigation() {

    searchRoute(
        onBackBtnClick = { backDispatcher ->
            backDispatcher?.onBackPressed()
        }
    )
}

fun NavGraphBuilder.searchRoute(
    onBackBtnClick: (OnBackPressedDispatcher?) -> Unit,
) {
    composable(route = NavigationConstants.SEARCH_ROUTE) {
        SearchScreen(
            onBackBtnClick = onBackBtnClick
        )
    }
}
