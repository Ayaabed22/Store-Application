package com.example.storeapplication.productDetails

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

class ProductDetailsViewModel:ViewModel() {

    val itemDetails = MutableLiveData<GetProductResponseItem>()

    @SuppressLint("LongLogTag")
    fun productDetails(productId:Int){
        val client = RetrofitClient.getInstance()!!.create(ProductsAPI::class.java)
        client.getProductDetails((productId).toString()).enqueue(object :
            Callback<GetProductResponseItem> {
            override fun onResponse(
                call: Call<GetProductResponseItem>,
                response: Response<GetProductResponseItem>
            ) {
                if (response.isSuccessful){
                    itemDetails.value = response.body()
//                    setData(response)
                    Log.i(TAG, "onResponse: " + response.body())
                }
            }

            override fun onFailure(call: Call<GetProductResponseItem>, t: Throwable) {
                Log.i(TAG, "onFailure: "+t.localizedMessage)
            }

        })
    }
    companion object {
        const val TAG = "ProductsDetailsViewModel"
    }
}