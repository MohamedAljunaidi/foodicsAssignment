package com.assignment.search

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.assignment.core.bases.BaseActivity
import com.assignment.navigation.extension.SetNavGraph
import com.assignment.navigation.navigation.constants.NavigationConstants
import com.assignment.search.navigation.searchNavigation
import com.assignment.theme.theme.FoodicsAssignmentTheme

class SearchActivity : BaseActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodicsAssignmentTheme {
                navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SetNavGraph(navController = navController,
                        startDestination = NavigationConstants.SEARCH_ROUTE,
                    ) {
                        searchNavigation()

                    }
                }
            }
        }
    }
}

