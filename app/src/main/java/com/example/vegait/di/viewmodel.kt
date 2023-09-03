package com.example.vegait.di

import com.example.vegait.usecase.AddProductUseCase
import com.example.vegait.usecase.DeleteProductUseCase
import com.example.vegait.usecase.ListProductUseCase
import com.example.vegait.usecase.ProductDetailUseCase
import com.example.vegait.usecase.UpdateProductUseCase
import com.example.vegait.viewmodel.ListProductsViewModel
import com.example.vegait.viewmodel.ProductDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {

    viewModel {
        ListProductsViewModel(ListProductUseCase(repository = get()))
    }

    viewModel {
        ProductDetailViewModel(
            ProductDetailUseCase(repository = get()),
            DeleteProductUseCase(repository = get()),
            UpdateProductUseCase(repository = get()),
            AddProductUseCase(repository = get())
        )
    }
}