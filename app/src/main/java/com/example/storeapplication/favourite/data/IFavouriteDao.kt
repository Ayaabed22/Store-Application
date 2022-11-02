package com.example.storeapplication.favourite.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IFavouriteDao {
    @Insert()
    fun insertItem(favouriteModel: FavouriteModel)

    @Query("SELECT * FROM Favourite")
    fun getFavourites():List<FavouriteModel>

    @Delete
    fun deleteItem(favouriteModel: FavouriteModel)

    @Query("DELETE FROM Favourite WHERE id = :itemId")
    fun deleteByItemId(itemId: Int)
}