package com.example.storeapplication.favourite.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favourite::class], version = 2, exportSchema = true)
abstract class FavouriteDatabase: RoomDatabase() {

    abstract fun favouriteDao():FavouriteDao

    companion object {
        private var instance: FavouriteDatabase?=null
        private const val database = "FavouriteDataBase"

        fun getDatabaseInstance(context: Context): FavouriteDatabase {
            instance?.let { return it }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouriteDatabase::class.java,
                    database)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                this.instance = instance
                return instance
            }
        }
    }
}