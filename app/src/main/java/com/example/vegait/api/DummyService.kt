package com.example.vegait.api

import retrofit2.http.DELETE
import retrofit2.http.GET
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
    @GET("/products")
    suspend fun products(): List<ProductsResponse>

    @PUT("/products/{id}")
    suspend fun update(@Path("id") id: Int): List<ProductUpdateResponse>

    @POST("/products/add")
    suspend fun create(): List<ProductCreatedResponse>

    @DELETE("/products/{id}")
    suspend fun delete(@Path("id") id: Int): List<ProductDeletedResponse>
}