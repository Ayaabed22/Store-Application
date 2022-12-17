package com.example.storeapplication.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapplication.home.data.Product
import com.example.storeapplication.utils.ErrorHandler.errorHandler
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val homeRepository: HomeRepository = HomeRepository()
    val productList = MutableLiveData<MutableList<Product>>()

    fun getProducts(){
        viewModelScope.launch (errorHandler) {
            productList.value = homeRepository.getProductsFromAPI().toMutableList()
        }
    }

    fun getProductsInSpecificCategory(categoryName: String){
        viewModelScope.launch (errorHandler) {
            productList.value = homeRepository.getProductsInCategory(categoryName).toMutableList()
        }
    }

    fun sortProducts(label:String) = homeRepository.sortProducts(label)?.toMutableList()

}