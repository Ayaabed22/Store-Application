package com.example.storeapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val retrofit: Retrofit? = null

    fun getClient() : FakeStoreAPI {

        if (retrofit == null) {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://fakestoreapi.com/")
                .build()
        }

        return retrofit!!.create(FakeStoreAPI::class.java)
    }
}