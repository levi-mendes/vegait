package com.example.vegait.di

import com.example.vegait.api.DummyApi
import com.example.vegait.api.DummyRepository
import com.example.vegait.api.RetrofitInitializer
//import org.koin.dsl.module
//
//val network = module {
//
//    single { RetrofitInitializer.getInstance() }
//    single { DummyApi(retrofit = get()) }
//    single { DummyRepository(api = get()) }
//}