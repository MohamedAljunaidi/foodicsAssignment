package com.assignment.foodicsassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignment.navigation.direction.hometab.HomeDestinationEnum
import com.assignment.navigation.direction.hometab.HomeNavigator
import com.assignment.navigation.extension.navigateToDirection
import com.assignment.theme.theme.FoodicsAssignmentTheme
import com.assignment.theme.theme.color
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MyApp()

        }
    }


@Composable
fun MyApp() {
    val context = LocalContext.current
    LaunchedEffect(true) {
        delay(3000L)
        HomeNavigator.navigateToDirection(
            context,
            destination = HomeDestinationEnum.Home
        )
        finish()
    }

    FoodicsAssignmentTheme {
        SplashScreen()
    }
}

@Composable
fun SplashScreen() {
    Surface(
        color = MaterialTheme.color.white,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "ic_logo",
            )
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello222 $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodicsAssignmentTheme {
        Greeting("Android")
    }
}
}
