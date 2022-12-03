package com.example.storeapplication.cart.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapplication.apiService.CartAPI
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.cart.data.CartResponse
import com.example.storeapplication.cart.data.ProductsItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel:ViewModel() {
    private val client = RetrofitClient.getInstance()!!.create(CartAPI::class.java)
    var cartItems = MutableLiveData<List<ProductsItem?>>()

    private var errorHandler:CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun getUserCart(id:Int) {
        viewModelScope.launch(errorHandler) {
            val userCarts = getUserCartFromApi(id)
            cartItems.value = createList(userCarts)
        }
    }
    private suspend fun getUserCartFromApi(id:Int)= withContext(Dispatchers.IO){
        client.getUserCarts(id)
    }

    private fun createList(responseBody:List<CartResponse>) =
        buildList {
            responseBody.forEach {
                it.products?.let { it1 ->
                    addAll(it1.toMutableList())
                }
            }
        }

}