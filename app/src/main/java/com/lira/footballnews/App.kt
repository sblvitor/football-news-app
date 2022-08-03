package com.lira.footballnews

import android.app.Application
import com.lira.footballnews.data.local.AppDatabase

class App: Application() {

    companion object{
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}