package com.example.vegait

import com.example.vegait.api.DummyRepositoryImpl

class UpdateProductUseCase(private val repository: DummyRepositoryImpl) {

    suspend fun update(product: ProductEntity): ProductEntity {
        return repository.update(product)
    }
}