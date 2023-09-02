package com.example.vegait

import com.example.vegait.api.DummyRepositoryImpl

class ProductDetailUseCase(private val repository: DummyRepositoryImpl) {

    suspend fun product(id: Int): ProductEntity {
        return repository.productDetail(id)
    }
}