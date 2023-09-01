package com.example.vegait.di

import com.example.vegait.ListProductUseCase
import com.example.vegait.api.ListProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {

    viewModel {
        ListProductsViewModel(ListProductUseCase(repository = get()))
    }
}