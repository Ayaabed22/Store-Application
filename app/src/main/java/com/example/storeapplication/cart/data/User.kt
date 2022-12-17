package com.example.storeapplication.cart.data

import com.example.storeapplication.signUp.data.Address

data class User(
	val id: Int? = null,
	val username: String? = null,
	val email    : String?  = null,
	val password : String?  = null,
	val phone    : String?  = null,
	val address  : Address? = null
)
