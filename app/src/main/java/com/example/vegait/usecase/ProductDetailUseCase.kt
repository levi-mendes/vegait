package com.example.vegait.usecase

import com.example.vegait.entity.ProductEntity
import com.example.vegait.repository.DummyRepositoryImpl

class ProductDetailUseCase(private val repository: DummyRepositoryImpl) {

    suspend fun product(id: Int): ProductEntity {
        return repository.productDetail(id)
    }
}