package com.example.storeapplication.cart.data /*TODO: add extra line below this and check for the other files*/
data class CartResponse(
    val date: String? = null,
    val id: Int? = null,
    val userId: Int? = null,
    val products: List<ProductsItem?>? = null
)

data class ProductsItem(val quantity: Int? = null, val productId: Int? = null)

