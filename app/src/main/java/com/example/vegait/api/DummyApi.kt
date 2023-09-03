package com.example.vegait.api

import com.example.vegait.api.request.UpdateProductRequest
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

//    suspend fun add(request: ProductAddRequest) = service.create()
//
    suspend fun update(product: UpdateProductRequest): ProductUpdateDTO {
        return service.update(product, product.id)
    }

    suspend fun delete(id: Int): ProductDeletedDTO {
        return service.delete(id)
    }
}