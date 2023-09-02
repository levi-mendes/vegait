package com.example.vegait.di

import com.example.vegait.ListProductUseCase
import com.example.vegait.ProductDetailUseCase
import com.example.vegait.api.ListProductsViewModel
import com.example.vegait.api.ProductDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {

    viewModel {
        ListProductsViewModel(ListProductUseCase(repository = get()))
    }

    viewModel {
        ProductDetailViewModel(ProductDetailUseCase(repository = get()))
    }
}