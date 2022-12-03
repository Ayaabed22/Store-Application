package com.example.storeapplication.utils

import com.example.storeapplication.favourite.data.IFavouriteDao
import kotlinx.coroutines.CoroutineExceptionHandler

class Const {
    companion object{
        lateinit var favouriteDao: IFavouriteDao /*TODO: This should be in the viewmodel*/
        /*TODO: Constants could be high-level because kotlin will create a class for them anyway*/
        const val mensCategory = "men's clothing" /*TODO: constant naming: MEN_CATEGORY*/
        const val womenCategory = "women's clothing"
        const val jeweleryCategory = "jewelery"
        const val electronicsCategory = "electronics"


        val errorHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()} /*TODO: this is not a constant*/
    }
}