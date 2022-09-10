package com.example.storeapplication.signup.data

data class SignUpResponse(
    var address: Address? = Address(),
    var id: Int? = null,
    var email: String? = null,
    var username: String? = null,
    var password: String? = null,
    var phone: String? = null
)


