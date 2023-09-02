package com.example.vegait

import com.example.vegait.api.DummyRepositoryImpl

class ListProductUseCase(private val repository: DummyRepositoryImpl) {

    suspend fun products(): List<ProductEntity> {
        return repository.products()
    }
}