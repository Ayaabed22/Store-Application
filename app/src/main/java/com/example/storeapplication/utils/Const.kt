package com.example.storeapplication.utils

import com.example.storeapplication.favourite.data.IFavouriteDao

class Const {
    companion object{
        lateinit var favouriteDao: IFavouriteDao
        const val mensCategory = "men's clothing"
        const val womenCategory = "women's clothing"
        const val jeweleryCategory = "jewelery"
        const val electronicsCategory = "electronics"
    }
}