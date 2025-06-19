package com.assignment.navigation.direction.hometab

import android.content.Context
import android.os.Bundle
import com.assignment.navigation.extension.startIntent
import com.assignment.navigation.navigation.bases.IBaseDestination
import com.assignment.navigation.navigation.bases.INavigatorDirection
import com.assignment.navigation.navigation.constants.NavigationConstants

object HomeNavigator : INavigatorDirection() {
    private const val HOME_ACTIVITY_PACKAGE_NAME = "hometab"
    private const val HOME_SCREEN_NAME = "HomeTabActivity"
    private const val FEATURE_HOME_PATH =
        "${NavigationConstants.FEATURE_PATH}.$HOME_ACTIVITY_PACKAGE_NAME"

    private const val HOME_PACKAGE_NAME =
        "$FEATURE_HOME_PATH.$HOME_SCREEN_NAME"

    override var className: String = HOME_PACKAGE_NAME

    override fun navigateTo(context: Context, data: Bundle?, destination: IBaseDestination?) {
        context.apply {
            startIntent(className, data, destination)
        }
    }
}
