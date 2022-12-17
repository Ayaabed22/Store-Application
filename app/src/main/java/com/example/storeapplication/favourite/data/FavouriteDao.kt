package com.example.storeapplication.favourite.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavouriteDao {
    @Insert
    suspend fun insertFavouriteItem(favourite: Favourite)

    @Query("SELECT * FROM Favourite")
    fun getFavourites():LiveData<List<Favourite>>

    @Query("DELETE FROM Favourite WHERE id = :favouriteItemId")
    suspend fun deleteFavouriteItem(favouriteItemId: Int)
}