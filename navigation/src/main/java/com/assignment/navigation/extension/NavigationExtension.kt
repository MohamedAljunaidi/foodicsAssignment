package com.assignment.navigation.extension

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.assignment.navigation.navigation.bases.IBaseDestination
import com.assignment.navigation.navigation.bases.INavigatorDirection
import com.assignment.navigation.navigation.constants.ExtraConstants
import com.assignment.navigation.navigation.model.DestinationHolder

internal fun Context.startIntent(
    classPath: String,
    data: Bundle? = null,
    destination: IBaseDestination? = null
) {

    val intent: Intent?
    try {
        intent = Intent(
            this,
            Class.forName(classPath)
        )
        val bundle = Bundle()

        data?.let { dataBundle ->
            bundle.putAll(dataBundle)
        }
        destination?.let { baseDestination ->
            val destinationHolder = DestinationHolder(baseDestination.getDestination())
            bundle.putParcelable(ExtraConstants.DESTINATION_KEY, destinationHolder)
        }
        intent.putExtras(bundle)
        startActivity(intent)
    } catch (e: ClassNotFoundException) {
        Log.e("startIntent", e.message ?: "")
    }
}

fun INavigatorDirection.navigateToDirection(
    context: Context,
    data: Bundle? = null,
    destination: IBaseDestination? = null
) {
    this.navigateTo(context, data, destination)
}

@Composable
fun SetNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String?=null,
    builder:  NavGraphBuilder.() -> Unit
) {

    val activity = LocalContext.current.getActivity()
    val destination =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            activity?.intent?.extras?.getParcelable(
                ExtraConstants.DESTINATION_KEY,
                DestinationHolder::class.java
            ) ?: DestinationHolder(startDestination)
        } else {
            activity?.intent?.extras?.getParcelable(ExtraConstants.DESTINATION_KEY) ?: DestinationHolder(
                startDestination
            )
        }

        NavHost(
            navController = navController,
            startDestination = destination.destinationId.toString(),
            builder = builder
        )
}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}