package com.example.vegait

import com.example.vegait.api.ProductAddRequest
import com.example.vegait.api.ProductCreatedResponse
import com.example.vegait.api.ProductDeletedResponse
import com.example.vegait.api.ProductUpdateResponse

interface DummyRepository {

    suspend fun products(): List<ProductEntity>
    suspend fun productDetail(id: Int): ProductEntity

    suspend fun add()

    suspend fun update()

    suspend fun delete(id: Int): ProductEntity
}
