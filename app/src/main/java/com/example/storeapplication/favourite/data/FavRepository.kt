package com.example.storeapplication.favourite.data

import androidx.lifecycle.LiveData

class FavRepository(private val dao: FavouriteDao) {

    suspend fun insertFavourite(favourite: Favourite){
        dao.insertFavouriteItem(favourite)
    }

    suspend fun getFavourites(): List<Favourite> {
        return dao.getFavourites()
    }

    suspend fun deleteFavourite(favouriteItemId: Int){
        dao.deleteFavouriteItem(favouriteItemId)
    }
}
