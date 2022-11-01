package com.example.storeapplication.favourite.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favourite")
data class FavouriteModel(
    @PrimaryKey val id:Int,
    @ColumnInfo (name= "Product Name")val title: String,
    @ColumnInfo (name= "Product Price")val price: Double,
    @ColumnInfo (name= "Product Image")val image: String
)
