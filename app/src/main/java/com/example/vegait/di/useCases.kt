package com.example.vegait.di

import com.example.vegait.ListProductUseCase
import org.koin.dsl.module

val useCases = module {

    single { ListProductUseCase(repository = get()) }
}