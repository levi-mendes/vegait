package com.example.vegait

import com.example.vegait.api.DummyRepositoryImpl

class DeleteProductUseCase(private val repository: DummyRepositoryImpl) {

    suspend fun deleteProduct(id: Int): ProductEntity {
        return repository.delete(id)
    }
}