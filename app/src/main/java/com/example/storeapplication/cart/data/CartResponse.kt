package com.example.storeapplication.cart.data

data class CartResponse(
    val date: String? = null,
    val id: Int? = null,
    val userId: Int? = null,
    val products: List<ProductsItem?>? = null
)/*TODO: the word response doesn't add to the meaning, please apply to other similar data classes*/

data class ProductsItem(val quantity: Int? = null, val productId: Int? = null)/*TODO: the word item doesn't add to the meaning*/

