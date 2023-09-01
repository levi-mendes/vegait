package com.example.vegait.api

class DummyRepository(private val api: DummyApi) {

    suspend fun list() = api.list()

    suspend fun add() = api.add()

    suspend fun update(id: Int) = api.update(id = id)

    suspend fun delete(id: Int) = api.delete(id = id)

}