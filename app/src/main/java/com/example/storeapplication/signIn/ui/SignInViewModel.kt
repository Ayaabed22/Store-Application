package com.example.storeapplication.signIn.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapplication.apiService.RetrofitClient
import com.example.storeapplication.apiService.UserAPI
import com.example.storeapplication.cart.data.User
import com.example.storeapplication.signIn.data.SignInRequest
import com.example.storeapplication.utils.ErrorHandler.errorHandler
import com.example.storeapplication.utils.MySharedPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel:ViewModel() {
    val isSuccessfulAuthentication = MutableLiveData<Boolean>()
    var userData = MutableLiveData<User?>()
    private val client = RetrofitClient.getInstance()!!.create(UserAPI::class.java)


    fun checkEnteredData(userName: String, password: String) {
        val loginRequest = SignInRequest(userName, password)
        viewModelScope.launch(errorHandler) {
            val response = login(loginRequest)
             isSuccessfulAuthentication.value = !response.token.isNullOrEmpty()
        }
    }

    private suspend fun login(loginRequest: SignInRequest) = withContext(Dispatchers.IO) {
        client.login(loginRequest)
    }


    fun getLoggedUserData(userName:String,context: Context){
        viewModelScope.launch (errorHandler) {
            val user = getUser(userName)
            userData.value = user
            safeUserDataOnSP(user,context)
        }
    }
    private suspend fun getUser(userName:String) = withContext(Dispatchers.IO){
        client.getAllUsers().find { it.username == userName }
    }

    private fun safeUserDataOnSP(user:User?, context: Context?) {
        val gson = Gson()
        val json = gson.toJson(user)
        MySharedPreferences.getPrefs(context)
        MySharedPreferences.saveBoolean(context, MySharedPreferences.KEY_MY_SHARED_BOOLEAN_LOGIN, true)
        MySharedPreferences.saveString(context, MySharedPreferences.KEY_MY_SHARED_String, json)
    }
}