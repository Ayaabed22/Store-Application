package com.example.storeapplication.cart.data

import com.example.storeapplication.signUp.data.Address

data class GetAllUsersResponse(
	val id: Int? = null,
	val username: String? = null,
	var email    : String?  = null,
	var password : String?  = null,
	var phone    : String?  = null,
	var address  : Address? = Address()
)
