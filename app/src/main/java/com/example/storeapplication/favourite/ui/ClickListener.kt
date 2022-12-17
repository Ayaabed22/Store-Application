package com.example.storeapplication.favourite.ui

import com.example.storeapplication.favourite.data.Favourite

interface ClickListener {

    fun onFavouriteIconClick(favourite: Favourite)
    fun onProductClick(productID: Int)
}