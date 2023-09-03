package com.example.vegait.usecase

import com.example.vegait.entity.ProductEntity
import com.example.vegait.repository.DummyRepositoryImpl

class ListProductUseCase(private val repository: DummyRepositoryImpl) {

    suspend fun products(): List<ProductEntity> {
        return repository.products()
    }
}