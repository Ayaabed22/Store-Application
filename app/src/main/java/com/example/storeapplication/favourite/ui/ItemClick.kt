package com.example.storeapplication.favourite.ui

interface ItemClick { /*TODO:interface and function names should be more explicit*/ //ProductClickListener

    fun favouriteClickListener(id:Int,name:String,price:Double,image:String)
    fun productClickListener(id:Int)
}