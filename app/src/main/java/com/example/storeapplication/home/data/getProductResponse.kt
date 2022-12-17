package com.example.storeapplication.home.data

data class Rating(val rate : Double, val count : Int)

data class Product(
	val id : Int,
	val title : String,
	val price : Double,
	val description : String,
	val category : String,
	val image : String,
	val rating : Rating,
	val quantity : Int?=null)
