package com.example.vegait

import android.app.Application
//import com.example.vegait.di.network
//import org.koin.android.ext.koin.androidContext
//import org.koin.core.context.startKoin

class VegaitApplication: Application() {

    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            modules(arrayListOf(
//                network
//            ))
//            androidContext(this@VegaitApplication)
//        }
    }
}