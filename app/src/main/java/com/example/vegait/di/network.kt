package com.example.vegait.di

import com.example.vegait.api.DummyApi
import com.example.vegait.repository.DummyRepositoryImpl
import com.example.vegait.api.RetrofitInitializer
import org.koin.dsl.module

val network = module {

    single { RetrofitInitializer.getInstance() }
    single { DummyApi(retrofit = get()) }
    single { DummyRepositoryImpl(api = get()) }
}