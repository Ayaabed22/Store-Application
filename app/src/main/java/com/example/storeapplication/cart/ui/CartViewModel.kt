package com.example.storeapplication.cart.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapplication.apiService.CartAPI
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.cart.data.CartResponse
import com.example.storeapplication.cart.data.ProductsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel:ViewModel() {
    private val client = RetrofitClient.getInstance()!!.create(CartAPI::class.java)
    var cartItems = MutableLiveData<List<ProductsItem?>>()
    private val tag = "Cart View Model"

    fun getUserCart(id:Int) {
        client.getUserCarts(id).enqueue(object : Callback<MutableList<CartResponse>> {
            override fun onResponse(
                call: Call<MutableList<CartResponse>>,
                response: Response<MutableList<CartResponse>>
            ) {
                if (response.isSuccessful) {
                    Log.i(tag, "onResponse: " + response.body())
                    response.body()?.let { responseBody ->
                        cartItems.value = buildList {
                            responseBody.forEach {
                                it.products?.let { it1 -> addAll(it1.toMutableList())
                                }
                            }
                        }
                        true
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<CartResponse>>, t: Throwable) {
                Log.i(tag, "onFailure: " + t.localizedMessage)
//                Toast.makeText(requireContext(), "Cart Failure", Toast.LENGTH_LONG).show()
            }
        })
    }
}