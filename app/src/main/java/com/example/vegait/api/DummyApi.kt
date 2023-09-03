package com.example.vegait.api

import com.example.vegait.api.request.ProductAddRequest
import com.example.vegait.api.request.UpdateProductRequest
import com.example.vegait.api.response.ProductCreatedDTO
import com.example.vegait.api.response.ProductDeletedDTO
import com.example.vegait.api.response.ProductDetailDTO
import com.example.vegait.api.response.ProductUpdateDTO
import com.example.vegait.api.response.ProductsDTO
import retrofit2.Retrofit

class DummyApi(retrofit: Retrofit) {

    private val service by lazy { retrofit.create(DummyService::class.java) }

    suspend fun list(): ProductsDTO {
        return service.products()
    }

    suspend fun productDetail(id: Int): ProductDetailDTO {
        return service.productDetail(id)
    }

    suspend fun add(request: ProductAddRequest): ProductCreatedDTO {
        return service.create(request = request)
    }

    suspend fun update(id: Int, product: UpdateProductRequest): ProductUpdateDTO {
        return service.update(product, id)
    }

    suspend fun delete(id: Int): ProductDeletedDTO {
        return service.delete(id)
    }
}