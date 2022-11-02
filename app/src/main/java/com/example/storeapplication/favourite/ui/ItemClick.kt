package com.example.storeapplication.favourite.ui

interface ItemClick {

    fun favouriteClickListener(id:Int,name:String,price:Double,image:String)

    fun productClickListener(id:Int)
}