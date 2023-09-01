package com.example.vegait.api

import com.example.vegait.DummyRepository
import com.example.vegait.Product

class DummyRepositoryImpl(private val api: DummyApi): DummyRepository {

    override suspend fun products(): List<Product> {
        return api.list().products
    }

    override suspend fun add() {

    }

    override suspend fun update() {

    }

    override suspend fun delete() {

    }
}