package com.assignment.caching.roomdb.common

import android.content.Context
import androidx.room.Room

object RoomFactory {

    fun createDatabaseRoom(
        context:Context
    ): DatabaseRoom {
        return  Room
            .databaseBuilder(context, DatabaseRoom::class.java, RoomConstants.DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
    }

}