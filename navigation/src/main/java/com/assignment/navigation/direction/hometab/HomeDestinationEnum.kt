package com.assignment.navigation.direction.hometab

import android.os.Parcelable
import com.assignment.navigation.navigation.bases.IBaseDestination
import com.assignment.navigation.navigation.constants.NavigationConstants
import kotlinx.parcelize.Parcelize

@Parcelize
enum class HomeDestinationEnum : Parcelable, IBaseDestination {

    Home {

        override fun getDestination(): String {
            return NavigationConstants.HOME_PATH
        }
    },



}

