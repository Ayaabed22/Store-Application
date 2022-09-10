package com.example.storeapplication.signup.data

data class Address (
    var geolocation: Geolocation? = Geolocation(),
    var city: String? = null,
    var street: String? = null,
    var number: Int? = null,
    var zipcode: String? = null
)
