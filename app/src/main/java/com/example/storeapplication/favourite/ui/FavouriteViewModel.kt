package com.example.storeapplication.favourite.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.storeapplication.favourite.data.Favourite
import com.example.storeapplication.favourite.data.FavouriteDatabase
import com.example.storeapplication.favourite.data.FavRepository
import com.example.storeapplication.utils.ErrorHandler.errorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteViewModel(application: Application):AndroidViewModel(application) {
    private val favRepository:FavRepository
    var favouriteList :MutableLiveData<MutableList<Favourite>> = MutableLiveData(mutableListOf())

    init {
        val dao = FavouriteDatabase.getDatabaseInstance(application).favouriteDao()
        favRepository = FavRepository(dao)
        viewModelScope.launch {
            val favList = favRepository.getFavourites()
            withContext(Dispatchers.Main) { favouriteList.value = favList.toMutableList() }
        }
    }

    private fun insertFavourite(favourite: Favourite){
        viewModelScope.launch (errorHandler) {
            favRepository.insertFavourite(favourite)
        }
    }

    private fun deleteFavourite(favouriteItemId: Int){
        viewModelScope.launch (errorHandler) {
            favRepository.deleteFavourite(favouriteItemId)
        }
    }

    fun updateFavouriteList(favourite: Favourite){
        var favouriteClicked = favouriteList.value?.find {
            it.id == favourite.id
        }
        Log.i("TAG", "updateFavouriteIcon: $favouriteClicked")
        favouriteClicked?.let {
            favouriteList.value?.remove(favourite)
            deleteFavourite(favourite.id)

        }?:run{
            favouriteList.value?.add(favourite)
            insertFavourite(favourite)
        }
    }
}