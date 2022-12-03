package com.example.storeapplication.cart.data

import com.example.storeapplication.signUp.data.Address

data class GetAllUsersResponse( /*TODO: change naming to User*/
	val id: Int? = null,
	val username: String? = null,
	var email    : String?  = null, /*TODO: are we sure we want this as var? please confirm and check for all data classes as well*/
	var password : String?  = null,
	var phone    : String?  = null,
	var address  : Address? = Address()  /*TODO: if the values are gonna be null then maybe not bother create an object, just initialise it with  null*/
)
/*TODO: [LEAVE IT TILL THE END] Use Ctrl+Alt+L to fix the formatting, please do that to all the files for better formatting*/
