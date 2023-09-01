package com.example.vegait.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInitializer {

    private const val BASE_URL = "https://dummyjson.com"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
    }

    private fun okHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(log())
            .build()
    }

    private fun log(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}