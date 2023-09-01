package com.example.vegait.api

import retrofit2.Retrofit

class DummyApi(retrofit: Retrofit) {

    private val service by lazy { retrofit.create(DummyService::class.java) }

    suspend fun list() = service.products()

    suspend fun add() = service.create()

    suspend fun update(id: Int) = service.update(id)

    suspend fun delete(id: Int) = service.delete(id)
}