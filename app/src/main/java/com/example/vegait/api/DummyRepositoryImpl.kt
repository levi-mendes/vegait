package com.example.vegait.api

import com.example.vegait.DummyRepository

import com.example.vegait.ProductEntity
import com.example.vegait.toEntity

class DummyRepositoryImpl(private val api: DummyApi): DummyRepository {

    override suspend fun products(): List<ProductEntity> {
        val response: ProductsResponse = api.list()
        return response.products.map { it.toEntity() }
    }

    override suspend fun productDetail(id: Int): ProductEntity {
        return api.productDetail(id).toEntity()
    }

    override suspend fun add() {

    }

    override suspend fun update() {

    }

    override suspend fun delete() {

    }
}