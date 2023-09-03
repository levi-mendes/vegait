package com.example.vegait.repository

import com.example.vegait.entity.ProductEntity

interface DummyRepository {

    suspend fun products(): List<ProductEntity>
    suspend fun productDetail(id: Int): ProductEntity

    suspend fun add()

    suspend fun update(product: ProductEntity): ProductEntity

    suspend fun delete(id: Int): ProductEntity
}
