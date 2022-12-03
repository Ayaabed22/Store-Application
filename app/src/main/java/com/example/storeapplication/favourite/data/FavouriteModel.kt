package com.example.storeapplication.favourite.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favourite")
data class FavouriteModel(  /*TODO: rename, the world Model, doesn't add to the meaning FavouriteProduct would make more meaning*/
    @PrimaryKey val id:Int,
    @ColumnInfo (name= "Product Name")val title: String,
    @ColumnInfo (name= "Product Price")val price: Double,
    @ColumnInfo (name= "Product Image")val image: String
)
