package com.assignment.foodicsassignment

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.assignment.core.bases.BaseActivity
import com.assignment.navigation.direction.hometab.HomeDestinationEnum
import com.assignment.navigation.direction.hometab.HomeNavigator
import com.assignment.navigation.extension.navigateToDirection

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {

            MyApp()

        }
    }


@Composable
fun MyApp() {
    val context = LocalContext.current
        HomeNavigator.navigateToDirection(
            context,
            destination = HomeDestinationEnum.Home
        )
        finish()

}

}
