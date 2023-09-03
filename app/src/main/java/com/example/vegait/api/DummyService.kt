package com.example.vegait.api

import com.example.vegait.api.request.ProductAddRequest
import com.example.vegait.api.request.UpdateProductRequest
import com.example.vegait.api.response.ProductCreatedDTO
import com.example.vegait.api.response.ProductDeletedDTO
import com.example.vegait.api.response.ProductDetailDTO
import com.example.vegait.api.response.ProductUpdateDTO
import com.example.vegait.api.response.ProductsDTO
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DummyService {

    @GET("products")
    suspend fun products(): ProductsDTO

    @GET("products/{id}")
    suspend fun productDetail(@Path("id") id: Int): ProductDetailDTO

    @Headers("Content-Type: application/json")
    @PUT("products/{id}")
    suspend fun update(@Body request: UpdateProductRequest, @Path("id") id: Int): ProductUpdateDTO

    @POST("products/add")
    suspend fun create(@Body request: ProductAddRequest): ProductCreatedDTO

    @DELETE("products/{id}")
    suspend fun delete(@Path("id") id: Int): ProductDeletedDTO
}