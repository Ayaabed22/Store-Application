package com.example.storeapplication.cart.data

data class Cart(
    val date: String? = null,
    val id: Int? = null,
    val userId: Int? = null,
    val products: List<Products?>? = null
)

data class Products(val quantity: Int? = null, val productId: Int? = null)

