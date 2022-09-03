package com.example.storeapplication.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@SerializedName("token")
	val token: String? = null
)
