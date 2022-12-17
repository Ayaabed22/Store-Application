package com.example.storeapplication.home.ui

import androidx.lifecycle.MutableLiveData
import com.example.storeapplication.apiService.ProductsAPI
import com.example.storeapplication.apiService.RetrofitClient
import com.example.storeapplication.home.data.Product
import com.example.storeapplication.utils.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository {
    private val itemList = MutableLiveData<MutableList<Product>>()
    private var client: ProductsAPI = RetrofitClient.getInstance()!!.create(ProductsAPI::class.java)

    suspend fun getProductsFromAPI() = withContext(Dispatchers.IO) { client.getProducts() }

    suspend fun getProductsInCategory(categoryName: String) = withContext(Dispatchers.IO) {
        client.getProductsInSpecificCategory(categoryName)
    }

    fun sortProducts(label:String): MutableList<Product>? {
        if (label == Const.SORT_BY_NAME){
            itemList.value?.sortBy { item-> item.title }
        }
        else if(label == Const.SORT_BY_PRICE)
        {
            itemList.value?.sortBy { item-> item.price }
        }
        return itemList.value
    }
}