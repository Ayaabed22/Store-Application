package com.example.storeapplication.productDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapplication.apiService.ProductsAPI
import com.example.storeapplication.apiService.RetrofitClient
import com.example.storeapplication.home.data.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailsViewModel : ViewModel() {

    val itemDetails = MutableLiveData<Product>()
    private val client = RetrofitClient.getInstance()!!.create(ProductsAPI::class.java)

    fun productDetails(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val productDetails = client.getProductDetails(productId.toString())
            withContext(Dispatchers.Main) {
                itemDetails.value = productDetails
            }
        }
    }
}