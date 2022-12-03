package com.example.storeapplication.productDetails

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapplication.apiService.ProductsAPI
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.home.data.GetProductResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailsViewModel:ViewModel() {

    val itemDetails = MutableLiveData<GetProductResponseItem>()
    private val tag = "ProductsDetailsViewModel"
    private val client = RetrofitClient.getInstance()!!.create(ProductsAPI::class.java)

    fun productDetails(productId:Int){
        viewModelScope.launch(Dispatchers.IO){
            val productDetails = client.getProductDetails(productId.toString())
            withContext(Dispatchers.Main){
                itemDetails.value = productDetails
            }
        }
    }
}