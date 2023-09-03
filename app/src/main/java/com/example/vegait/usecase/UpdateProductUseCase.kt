package com.example.vegait.usecase

import com.example.vegait.entity.ProductEntity
import com.example.vegait.repository.DummyRepositoryImpl

class UpdateProductUseCase(private val repository: DummyRepositoryImpl) {

    suspend fun update(product: ProductEntity): ProductEntity {
        return repository.update(product)
    }
}