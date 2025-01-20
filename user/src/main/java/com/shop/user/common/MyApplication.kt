package com.shop.user.common

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (com.shop.user.BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}