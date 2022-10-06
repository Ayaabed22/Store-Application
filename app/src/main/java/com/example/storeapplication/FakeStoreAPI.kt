package com.example.storeapplication

import com.example.storeapplication.login.LoginRequest
import com.example.storeapplication.login.LoginResponse
import com.example.storeapplication.signUp.SignUpRequest
import com.example.storeapplication.signUp.SignUpResponse
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface FakeStoreAPI {

    @GET("/products")
    fun getProducts() : Call<MutableList<GetProductResponseItem>>

    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequest ) : Call<LoginResponse>

    @POST("/users")
    fun signUp(@Body signUpRequest: SignUpRequest) : Call<SignUpResponse>

    @GET("/users")
    fun getProfile(@Header("Auth") auth: String) : Call<SignUpRequest>

}