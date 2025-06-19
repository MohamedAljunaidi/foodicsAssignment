package com.assignment.navigation.direction.search

import android.os.Parcelable
import com.assignment.navigation.navigation.bases.IBaseDestination
import com.assignment.navigation.navigation.constants.NavigationConstants
import kotlinx.parcelize.Parcelize



@Parcelize
enum class SearchDestinationEnum : Parcelable, IBaseDestination {

    SEARCH {

        override fun getDestination(): String {
            return NavigationConstants.SEARCH_ROUTE
        }
    },

}

