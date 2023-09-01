package com.example.vegait

import android.app.Application
import com.example.vegait.di.appModule
import com.example.vegait.di.network
import com.example.vegait.di.useCases
import com.example.vegait.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VegaitApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(arrayListOf(
                network,
                useCases,
                appModule,
                viewModel
            ))
            androidContext(this@VegaitApplication)
        }
    }
}