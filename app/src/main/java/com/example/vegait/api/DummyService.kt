package com.example.vegait.api

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DummyService {
    //https://dummyjson.com
    /**
     *
     * [endpoint products](https://dummyjson.com/docs/products)
     *
     * @return
     */
    @GET("products")
    suspend fun products(): ProductsResponse

    @GET("products/{id}")
    suspend fun productDetail(@Path("id") id: Int): ProductDetailResponse

    @Headers("Content-Type: application/json")
    @PUT("products/{id}")
    suspend fun update(@Body request: UpdateProductRequest, @Path("id") id: Int): ProductUpdateResponse

    //@POST("products/add")
    //suspend fun create(/*@Body request: ProductAddRequest*/)

    @DELETE("products/{id}")
    suspend fun delete(@Path("id") id: Int): ProductDeletedResponse
}