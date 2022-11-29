package com.example.storeapplication.home.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapplication.apiService.ProductsAPI
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.home.data.GetProductResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
//
//    class HomeViewModel: ViewModel() {
//
//        private var client: ProductsAPI
//
//        val itemList = MutableLiveData<MutableList<GetProductResponseItem>>()
//
//        init {
//            client = RetrofitClient.getInstance()!!.create(ProductsAPI::class.java)
//        }
//
//        fun getProducts(){
//            viewModelScope.launch(Dispatchers.IO) {
//                val products = client.getProducts()
//                withContext(Dispatchers.Main) {
//                    itemList.value = products
//                }
//            }
//        }
//
//    }
//@GET("/products")
//fun getProducts() : MutableList<GetProductResponseItem>
    
    val itemList = MutableLiveData<MutableList<GetProductResponseItem>>()
    private val client = RetrofitClient.getInstance()!!.create(ProductsAPI::class.java)
    private val tag = "Home View Model"


    fun getProducts(){
        client.getProducts().enqueue(object:
            Callback<MutableList<GetProductResponseItem>> {
            override fun onResponse(call: Call<MutableList<GetProductResponseItem>>,
                                    response: Response<MutableList<GetProductResponseItem>>)
            {
                if (response.isSuccessful) {
                    itemList.value = response.body()
                    Log.i(tag, "onResponse: "+ response.body())
                }
            }
            override fun onFailure(call: Call<MutableList<GetProductResponseItem>>, t: Throwable) {
                Log.i(tag, "onFailure: " + t.localizedMessage)
            }
        })
    }

    fun productsInSpecificCategory(categoryName:String){
        client.getProductsInSpecificCategory(categoryName).enqueue(object:
            Callback<MutableList<GetProductResponseItem>> {
            override fun onResponse(
                call: Call<MutableList<GetProductResponseItem>>,
                response: Response<MutableList<GetProductResponseItem>>
            ) {
                if (response.isSuccessful){
                    itemList.value = response.body()
                    Log.i(tag, "onResponse: "+response.body())
                }
            }
            override fun onFailure(call: Call<MutableList<GetProductResponseItem>>, t: Throwable) {
                Log.i(tag, "onFailure: " + t.localizedMessage)
            }
        })
    }
}