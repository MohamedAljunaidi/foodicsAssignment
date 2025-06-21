package com.assignment.hometab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.assignment.hometab.navigation.homeNavigation
import com.assignment.navigation.extension.SetNavGraph
import com.assignment.navigation.navigation.constants.NavigationConstants
import com.assignment.theme.component.ScaffoldTopAppbar
import com.assignment.theme.theme.AppTheme
import com.assignment.theme.theme.FoodicsAssignmentTheme
import com.assignment.theme.theme.color

class HomeTabActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodicsAssignmentTheme {
                navController = rememberNavController()

                var selectedTabIndex by remember { mutableIntStateOf(0) }

                val bottomNavItems = listOf(
                    BottomNavItem.Home(stringResource(id = R.string.title_table)),
                    BottomNavItem.Orders(stringResource(id = R.string.title_orders)),
                    BottomNavItem.Menu(stringResource(id = R.string.title_menu)),
                    BottomNavItem.Settings(stringResource(id = R.string.title_settings)),
                )
                val selectedTabTitle = bottomNavItems[selectedTabIndex].name

                ScaffoldTopAppbar(
                    title = selectedTabTitle,
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController,
                            items = bottomNavItems,
                            selectedTabIndex = selectedTabIndex,
                            onTabSelected = { selectedTabIndex = it }
                        )
                    }
                ) { innerPadding ->
                    SetNavGraph(
                        navController = navController,
                        startDestination = NavigationConstants.HOME_PATH,
                    ) {
                        homeNavigation(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<BottomNavItem>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Surface(
        border = BorderStroke(.5.dp, MaterialTheme.color.white),
        shape = RoundedCornerShape(
            topStart = AppTheme.dimens.paddingLarge,
            topEnd = AppTheme.dimens.paddingLarge
        )
    ) {
        NavigationBar(
            tonalElevation = 8.dp,
            containerColor = MaterialTheme.color.navigationColor,
            windowInsets = WindowInsets(0.dp)
        ) {
            items.forEachIndexed { index, screen ->
                val isSelected = index == selectedTabIndex

                NavigationBarItem(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = if (isSelected) screen.selectedIcon else screen.unSelectedIcon
                            ),
                            contentDescription = screen.name,
                            modifier = Modifier.size(AppTheme.dimens.navIconSize)
                        )
                    },
                    label = {
                        Text(
                            text = screen.name,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = AppTheme.dimens.navLabelFontSize
                            )
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        if (currentRoute != screen.route) {
                            onTabSelected(index)
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.color.black,
                        selectedTextColor = MaterialTheme.color.black,
                        unselectedIconColor = MaterialTheme.color.black,
                        unselectedTextColor = MaterialTheme.color.black,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}
sealed class BottomNavItem(
    val name: String,
    val route: String,
    val selectedIcon: Int,
    val unSelectedIcon: Int
) {
    data class Home(val title: String) :
        BottomNavItem(
            name = title,
            route = NavigationConstants.HOME_PATH,

            selectedIcon = R.drawable.ic_table_selected,
            unSelectedIcon = R.drawable.ic_table_unselected
        )

    data class Orders(val title: String) :
        BottomNavItem(
            title,
            NavigationConstants.ORDERS_PATH,
            R.drawable.ic_orders_selected,
            R.drawable.ic_orders_unselected
        )

    data class Menu(val title: String) :
        BottomNavItem(
            title,
            NavigationConstants.MENU_PATH,
            R.drawable.ic_menu_selected,
            R.drawable.ic_menu_unselected
        )

    data class Settings(val title: String) :
        BottomNavItem(
            title,
            NavigationConstants.SETTINGS_PATH,
            R.drawable.ic_settings_selected,
            R.drawable.ic_settings_unselected
        )
}
