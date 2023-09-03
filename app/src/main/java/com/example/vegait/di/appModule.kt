package com.example.vegait.di

import com.example.vegait.repository.DummyRepository
import com.example.vegait.repository.DummyRepositoryImpl
import org.koin.dsl.module

val appModule = module {

    single <DummyRepository>{ DummyRepositoryImpl(api = get()) }
}