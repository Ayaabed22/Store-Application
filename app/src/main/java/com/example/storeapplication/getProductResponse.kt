package com.example.storeapplication

import com.google.gson.annotations.SerializedName

data class Rating(

	@SerializedName("rate") val rate : Double,
	@SerializedName("count") val count : Int)

data class GetProductResponseItem(
	@SerializedName("id") val productId : Int,
	@SerializedName("id") val id : Int,
	@SerializedName("title") val title : String,
	@SerializedName("price") val price : Double,
	@SerializedName("description") val description : String,
	@SerializedName("category") val category : String,
	@SerializedName("image") val image : String,
	@SerializedName("rating") val rating : Rating,
	val quantity : Int?=null)
