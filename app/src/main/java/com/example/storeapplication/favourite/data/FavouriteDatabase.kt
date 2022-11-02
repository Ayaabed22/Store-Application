package com.example.storeapplication.favourite.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavouriteModel::class], version = 2, exportSchema = true)
abstract class FavouriteDatabase: RoomDatabase() {

    abstract fun favouriteDao():IFavouriteDao

    companion object {
        private var Instance: FavouriteDatabase?=null
        fun getDatabaseInstance(context: Context): FavouriteDatabase {
            var temInstance = Instance
            if (temInstance!=null){
                return temInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouriteDatabase::class.java,
                    "FavouriteDataBase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                Instance = instance
                    return instance
            }
        }
    }
}