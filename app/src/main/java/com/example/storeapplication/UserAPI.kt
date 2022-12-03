package com.example.storeapplication

import com.example.storeapplication.cart.data.GetAllUsersResponse
import com.example.storeapplication.signIn.data.SignInRequest
import com.example.storeapplication.signIn.data.SignInResponse
import com.example.storeapplication.signUp.data.SignUpRequest
import com.example.storeapplication.signUp.data.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserAPI {
    @POST("/auth/login")
    fun login(@Body signInRequest: SignInRequest) : Call<SignInResponse>

    @POST("/users")
    fun signUp(@Body signUpRequest: SignUpRequest) : Call<SignUpResponse>

    @GET("/users/{id}")
    fun getUserData(@Path("id") id:String): Call<GetAllUsersResponse>

    @GET("/users")
    fun getAllUsers(): Call<MutableList<GetAllUsersResponse>>
}