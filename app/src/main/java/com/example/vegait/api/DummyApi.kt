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
//    suspend fun update(id: Int) = service.update()
//
    suspend fun delete(id: Int): ProductDeletedResponse {
        return service.delete(id)
    }
}