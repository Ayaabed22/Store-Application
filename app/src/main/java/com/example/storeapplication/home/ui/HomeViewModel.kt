package com.example.storeapplication.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapplication.apiService.ProductsAPI
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.home.data.GetProductResponseItem
import com.example.storeapplication.utils.Const.Companion.errorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    val itemList = MutableLiveData<MutableList<GetProductResponseItem>>()
    private var client: ProductsAPI = RetrofitClient.getInstance()!!.create(ProductsAPI::class.java)


    fun getProducts() {
        viewModelScope.launch(errorHandler) {
            val products = getProductsFromAPI()
            itemList.value = products.toMutableList()
        }
    }

    private suspend fun getProductsFromAPI() = withContext(Dispatchers.IO) { client.getProducts() }
    private suspend fun getProductsInCategory(categoryName: String) = withContext(Dispatchers.IO) {
        client.getProductsInSpecificCategory(categoryName)
    }


    fun productsInSpecificCategory(categoryName: String) {
        viewModelScope.launch(errorHandler) {
            val productsInCategory = getProductsInCategory(categoryName)
            itemList.value = productsInCategory.toMutableList()
        }
    }
}