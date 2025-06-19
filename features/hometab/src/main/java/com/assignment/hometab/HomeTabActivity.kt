package com.assignment.hometab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.assignment.hometab.navigation.homeNavigation
import com.assignment.navigation.extension.SetNavGraph
import com.assignment.navigation.navigation.constants.NavigationConstants
import com.assignment.theme.theme.FoodicsAssignmentTheme
import com.assignment.theme.theme.color

class HomeTabActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodicsAssignmentTheme {
                navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
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
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home(stringResource(id = R.string.title_table)),
        BottomNavItem.Orders(stringResource(id = R.string.title_orders)),
        BottomNavItem.Menu(stringResource(id = R.string.title_menu)),
        BottomNavItem.Settings(stringResource(id = R.string.title_settings)),
    )

    val startDestination = items.first()
    var selectedTab by remember {
        mutableIntStateOf(items.indexOf(startDestination))
    }
    Surface(
        border = BorderStroke(.5.dp, MaterialTheme.color.white),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        NavigationBar(
            tonalElevation = 8.dp,
            containerColor = MaterialTheme.color.fieldColor
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEachIndexed { index, screen ->
                if (currentRoute == NavigationConstants.HOME_PATH) {
                    selectedTab = 0
                }
                val isSelected = index == selectedTab

                NavigationBarItem(
                    icon = { Icon(screen.icon, contentDescription = screen.name) },
                    label = { Text(screen.name) },
                    selected = isSelected,
                    onClick = {
                        if (currentRoute != screen.route) {
                            selectedTab = index
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },

                    colors = NavigationBarItemDefaults
                        .colors(
                            selectedIconColor = MaterialTheme.color.selectedColor,
                            selectedTextColor = MaterialTheme.color.selectedColor,
                            unselectedIconColor = MaterialTheme.color.unSelectedColor,
                            unselectedTextColor = MaterialTheme.color.unSelectedColor,
                            indicatorColor = MaterialTheme.color.fieldColor
                        )
                )
            }
        }
    }
}

sealed class BottomNavItem(val name: String, val route: String, val icon: ImageVector) {
    data class Home(val title: String) :
        BottomNavItem(title, NavigationConstants.HOME_PATH, Icons.Default.Home)

    data class Orders(val title: String) :
        BottomNavItem(title, NavigationConstants.ORDERS_PATH, Icons.Default.Favorite)

    data class Menu(val title: String) :
        BottomNavItem(title, NavigationConstants.MENU_PATH, Icons.Default.Favorite)

    data class Settings(val title: String) :
        BottomNavItem(title, NavigationConstants.SETTINGS_PATH, Icons.Default.Favorite)


}