package com.example.vegait

import com.example.vegait.api.ProductAddRequest
import com.example.vegait.api.ProductCreatedResponse
import com.example.vegait.api.ProductDeletedResponse
import com.example.vegait.api.ProductUpdateResponse

interface DummyRepository {

    suspend fun products(): List<Product>

    suspend fun add()

    suspend fun update()

    suspend fun delete()
}
