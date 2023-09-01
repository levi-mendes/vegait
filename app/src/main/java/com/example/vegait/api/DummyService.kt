package com.example.vegait.api

import retrofit2.http.Body
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
    @GET("products")
    suspend fun products(): ProductsResponse

    //@PUT("products/{id}")
    //suspend fun update(/*@Path("id") id: Int*/)

    //@POST("products/add")
    //suspend fun create(/*@Body request: ProductAddRequest*/)

    //@DELETE("products/{id}")
    //suspend fun delete(/*@Path("id") id: Int*/)
}