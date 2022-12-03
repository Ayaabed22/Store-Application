package com.example.storeapplication.favourite.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IFavouriteDao { /*TODO: change ambiguous naming "IFavouriteDao"*/
    @Insert() /*TODO: remove brackets*/
    fun insertItem(favouriteModel: FavouriteModel) /*TODO: change ambiguous naming "insertItem"*/

    @Query("SELECT * FROM Favourite")
    fun getFavourites():List<FavouriteModel>

    @Delete
    fun deleteItem(favouriteModel: FavouriteModel) /*TODO: clean up unused method, although passing the item would have been sufficient to delete the next method*/

    @Query("DELETE FROM Favourite WHERE id = :itemId")
    fun deleteByItemId(itemId: Int)
}