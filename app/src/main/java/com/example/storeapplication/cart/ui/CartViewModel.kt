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
    }/*TODO: add an extra line here, and please check the rest of the files for proper spacing between the functions*/
    private suspend fun getUserCartFromApi(id:Int)= withContext(Dispatchers.IO){
        client.getUserCarts(id)
    }

    private fun createList(responseBody:List<CartResponse>) =
        buildList { /*TODO: several nesting is done here, could be reduced */
            responseBody.forEach {
                it.products?.let { it1 -> /*TODO: it1 seems ambiguous, either keep it as 'it' or give a meaningful name*/
                    addAll(it1.toMutableList())
                }
            }
        }
   /*
    private fun createList2(responseBody: List<CartResponse>) = buildList {
        responseBody.filter { it.products != null }.forEach {
            addAll(it.products?.toMutableList() ?: emptyList())
        }
    }*/
}