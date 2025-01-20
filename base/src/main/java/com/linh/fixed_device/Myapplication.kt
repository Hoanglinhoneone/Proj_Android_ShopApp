package com.linh.fixed_device

import android.app.Application
import timber.log.Timber

class Myapplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}