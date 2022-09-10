package com.example.storeapplication

data class Rating(
	val rate: Double,
	val count: Int
)

data class ProductItem(
	val id: Int,
	val title: String,
	val price: Double,
	val description: String,
	val category: String,
	val image: String,
	val rating: Rating
)
