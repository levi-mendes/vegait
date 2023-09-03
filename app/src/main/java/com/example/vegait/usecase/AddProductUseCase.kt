package com.example.vegait.usecase

import com.example.vegait.entity.ProductCreatedEntity
import com.example.vegait.repository.DummyRepositoryImpl

class AddProductUseCase(private val repository: DummyRepositoryImpl) {

    suspend fun addProduct(title: String): ProductCreatedEntity {
        return repository.add(title)
    }
}