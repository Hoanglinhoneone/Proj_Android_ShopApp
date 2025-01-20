package com.shop.user.common

import android.app.Application
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (com.shop.user.BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}