package com.assignment.hometab.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.assignment.hometab.menu.MenuScreen
import com.assignment.hometab.orders.OrdersScreen
import com.assignment.hometab.settings.SettingsScreen
import com.assignment.hometab.tables.TablesScreen
import com.assignment.navigation.navigation.constants.NavigationConstants


fun NavGraphBuilder.homeNavigation(
    modifier: Modifier = Modifier
) {

    tablesRoute(
        modifier = modifier,
    )

    ordersRoute()
    menuRoute()
    settingsRoute()

}

fun NavGraphBuilder.tablesRoute(
    modifier: Modifier = Modifier,
) {
    composable(route = NavigationConstants.HOME_PATH) {
        TablesScreen(
            modifier = modifier,
        )
    }
}


fun NavGraphBuilder.ordersRoute(
) {
    composable(route = NavigationConstants.ORDERS_PATH) {
        OrdersScreen()
    }
}

fun NavGraphBuilder.menuRoute(
) {
    composable(route = NavigationConstants.MENU_PATH) {

        MenuScreen()
    }
}

fun NavGraphBuilder.settingsRoute(
) {
    composable(route = NavigationConstants.SETTINGS_PATH) {
        SettingsScreen()
    }
}
