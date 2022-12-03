package com.example.storeapplication.favourite.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavouriteModel::class], version = 2, exportSchema = true)
abstract class FavouriteDatabase: RoomDatabase() {

    abstract fun favouriteDao():IFavouriteDao

    companion object {
        private var Instance: FavouriteDatabase?=null /*TODO: we shouldn't have variables named in PascalCase, should be 'instance' instead*/
        /*TODO: Leaving an empty line here would be helpful in readability*/
        fun getDatabaseInstance(context: Context): FavouriteDatabase {
            var temInstance = Instance /*TODO: temInstance is not understandable, naming-wise & also why is it there? */
            if (temInstance!=null){
                return temInstance
            } /*TODO: these three lines could be replaced with: instance?.let { return it } */
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouriteDatabase::class.java,
                    "FavouriteDataBase") /*TODO: Better to have this as a constant instead of a hardcoded string*/
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                Instance = instance /*TODO: a new variable wasn't necessary, could have set the "Instance" directly*/
                    return instance /*TODO: To have a single-source of truth, better to return your main variable 'Instance'*/
            }
        }
    }
}