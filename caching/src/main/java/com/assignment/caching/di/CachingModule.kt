package com.assignment.caching.di

import android.content.Context
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.roomdb.common.DatabaseRoom
import com.assignment.caching.roomdb.common.RoomFactory
import com.assignment.caching.roomdb.common.RoomManager
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.assignment.caching.di")
class CachingModule {

    @Single
    fun provideDatabase(appContext: Context): DatabaseRoom {
        return RoomFactory.createDatabaseRoom(appContext)
    }
    @Single
    fun provideRoomManage(databaseRoom: DatabaseRoom): RoomManager {
        return RoomManager(databaseRoom)
    }

    @Single
    fun provideCachingManager(
        roomManager: RoomManager
    ): CachingManager {
        return CachingManager(roomManager)
    }
}