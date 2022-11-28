package com.example.storeapplication.home.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapplication.ProductsAPI
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.home.data.GetProductResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsCategoryViewModel :ViewModel() {
    val itemList = MutableLiveData<MutableList<GetProductResponseItem>>()

    @SuppressLint("LongLogTag")
    fun productsInSpesificCategory(categoryName:String){
        val client = RetrofitClient.getInstance()!!.create(ProductsAPI::class.java)
        client.getProductsInSpecificCategory(categoryName).enqueue(object:
            Callback<MutableList<GetProductResponseItem>> {
            override fun onResponse(
                call: Call<MutableList<GetProductResponseItem>>,
                response: Response<MutableList<GetProductResponseItem>>
            ) {
                if (response.isSuccessful){
                    itemList.value = response.body()
                    Log.i(TAG, "onResponse: "+response.body())
                }
            }
            override fun onFailure(call: Call<MutableList<GetProductResponseItem>>, t: Throwable) {
                Log.i(TAG, "onFailure: " + t.localizedMessage)
            }
        })
    }
    companion object {
        const val TAG = "ProductsCategoryViewModel"
    }
}