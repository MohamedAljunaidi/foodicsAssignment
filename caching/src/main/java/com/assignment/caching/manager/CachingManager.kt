package com.assignment.caching.manager

import com.assignment.caching.roomdb.common.RoomManager


class CachingManager(
    private val roomManager: RoomManager
) {
    // The getProvider method is to choose what caching manager you want to provide.
    // The provider parameter is an enum class that includes every type of caching.
    fun getProvider(provider: ProviderEnum): BaseManager {
        return when (provider) {
            ProviderEnum.ROOM -> roomManager
        }
    }
}