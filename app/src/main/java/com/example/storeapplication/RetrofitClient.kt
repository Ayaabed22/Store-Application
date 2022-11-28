package com.example.storeapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 object RetrofitClient {

        private var retrofit: Retrofit? = null

        fun  getInstance() : Retrofit? {

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://fakestoreapi.com/")
                    .build()
            }

            return retrofit
        }
}