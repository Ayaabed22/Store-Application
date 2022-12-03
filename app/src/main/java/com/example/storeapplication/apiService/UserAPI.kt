package com.example.storeapplication.apiService

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
    suspend fun login(@Body signInRequest: SignInRequest) :SignInResponse

    @POST("/users")
    fun signUp(@Body signUpRequest: SignUpRequest) : SignUpResponse

    @GET("/users/{id}")
    suspend fun getUserData(@Path("id") id:String): GetAllUsersResponse

    @GET("/users")
    suspend fun getAllUsers(): List<GetAllUsersResponse>
}