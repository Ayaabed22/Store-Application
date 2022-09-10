package com.example.storeapplication

import com.example.storeapplication.login.data.LoginRequest
import com.example.storeapplication.login.data.LoginResponse
import com.example.storeapplication.signup.data.SignUpRequest
import com.example.storeapplication.signup.data.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequest) : Call<LoginResponse>

    @POST("/users")
    fun signUp(@Body signUpRequest: SignUpRequest) : Call<SignUpResponse>

}