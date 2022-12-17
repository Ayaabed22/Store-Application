package com.example.storeapplication.cart.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapplication.apiService.CartAPI
import com.example.storeapplication.apiService.RetrofitClient
import com.example.storeapplication.cart.data.Cart
import com.example.storeapplication.cart.data.Products
import com.example.storeapplication.utils.ErrorHandler.errorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel:ViewModel() {
    private val client = RetrofitClient.getInstance()!!.create(CartAPI::class.java)
    var cartItems = MutableLiveData<List<Products?>>()

    fun getUserCart(id:Int) {
        viewModelScope.launch(errorHandler) {
            val userCarts = getUserCartFromApi(id)
            cartItems.value = createList(userCarts)
        }
    }

    private suspend fun getUserCartFromApi(id:Int)= withContext(Dispatchers.IO){
        client.getUserCarts(id)
    }

    private fun createList(responseBody: List<Cart>) = buildList {
        responseBody.filter { it.products != null }.forEach {
            addAll(it.products?.toMutableList() ?: emptyList())
        }
    }
}