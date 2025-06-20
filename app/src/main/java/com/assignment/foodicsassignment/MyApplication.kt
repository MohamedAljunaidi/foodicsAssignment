package com.assignment.foodicsassignment

import android.app.Application
import com.assignment.caching.di.CachingModule
import com.assignment.hometab.tables.di.CategoriesModule
import com.assignment.network.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.*

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            androidLogger()
            androidFileProperties()

            modules(listOf(NetworkModule().module, CachingModule().module, CategoriesModule().module, defaultModule))

        }
    }

}