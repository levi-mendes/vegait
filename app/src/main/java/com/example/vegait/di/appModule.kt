package com.example.vegait.di

import com.example.vegait.DummyRepository
import com.example.vegait.api.DummyRepositoryImpl
import org.koin.dsl.module

val appModule = module {

    single <DummyRepository>{ DummyRepositoryImpl(api = get()) }
}