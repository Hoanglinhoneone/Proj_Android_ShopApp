package com.shop.user.common

import android.app.Application
import androidx.room.Room
import com.shop.user.data.local.AppDatabase
import timber.log.Timber

class MyApplication : Application() {
    companion object {
        lateinit var appDatabase: AppDatabase
    }
    override fun onCreate() {
        super.onCreate()
        if (com.shop.user.BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "shop-db"
        ).build()
    }
}