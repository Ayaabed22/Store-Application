package com.example.storeapplication.signup.data

data class SignUpRequest(
    var email: String? = null,
    var username: String? = null,
    var password: String? = null,
    var name: Name? = Name(),
    var address: Address? = Address(),
    var phone: String? = null
)

data class Name(
    var firstname : String? = null,
    var lastname  : String? = null
)