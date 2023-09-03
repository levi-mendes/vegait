package com.example.vegait.usecase

import com.example.vegait.entity.ProductEntity
import com.example.vegait.repository.DummyRepositoryImpl

class DeleteProductUseCase(private val repository: DummyRepositoryImpl) {

    suspend fun deleteProduct(id: Int): ProductEntity {
        return repository.delete(id)
    }
}