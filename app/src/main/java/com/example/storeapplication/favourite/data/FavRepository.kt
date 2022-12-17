package com.example.storeapplication.favourite.data

import androidx.lifecycle.LiveData

class FavRepository(private val dao: FavouriteDao) {

    suspend fun insertFavourite(favourite: Favourite){
        dao.insertFavouriteItem(favourite)
    }

    val getFavourites:LiveData<List<Favourite>> = dao.getFavourites()

    suspend fun deleteFavourite(favouriteItemId: Int){
        dao.deleteFavouriteItem(favouriteItemId)
    }
}
