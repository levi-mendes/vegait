package com.example.vegait.api

import retrofit2.Retrofit

class DummyApi(retrofit: Retrofit) {

    private val service by lazy { retrofit.create(DummyService::class.java) }

    suspend fun list(): ProductsResponse {
        return service.products()
    }

    suspend fun productDetail(id: Int): ProductDetailResponse {
        return service.productDetail(id)
    }

//    suspend fun add(request: ProductAddRequest) = service.create()
//
    suspend fun update(product: UpdateProductRequest): ProductUpdateResponse {
        return service.update(product, product.id)
    }

    suspend fun delete(id: Int): ProductDeletedResponse {
        return service.delete(id)
    }
}