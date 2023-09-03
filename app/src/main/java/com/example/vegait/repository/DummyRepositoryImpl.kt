package com.example.vegait.repository

import com.example.vegait.entity.ProductEntity
import com.example.vegait.api.DummyApi
import com.example.vegait.api.response.ProductDeletedDTO
import com.example.vegait.api.response.ProductsDTO
import com.example.vegait.toEntity
import com.example.vegait.toProductRequest

class DummyRepositoryImpl(private val api: DummyApi): DummyRepository {

    override suspend fun products(): List<ProductEntity> {
        val response: ProductsDTO = api.list()
        return response.products.map { it.toEntity() }
    }

    override suspend fun productDetail(id: Int): ProductEntity {
        return api.productDetail(id).toEntity()
    }

    override suspend fun add() {

    }

    override suspend fun update(product: ProductEntity): ProductEntity {
        val response: ProductEntity =
            api.update(product = product.toProductRequest()).toEntity()

        return response
    }

    override suspend fun delete(id: Int): ProductEntity {
        val response: ProductDeletedDTO = api.delete(id)
        return response.toEntity()
    }
}