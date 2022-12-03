package com.example.storeapplication.signUp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.apiService.UserAPI
import com.example.storeapplication.signIn.data.SignInRequest
import com.example.storeapplication.signUp.data.SignUpRequest
import com.example.storeapplication.signUp.data.SignUpResponse
import com.example.storeapplication.utils.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    val isSuccessfulSignUp = MutableLiveData<Boolean>()
    val client = RetrofitClient.getInstance()!!.create(UserAPI::class.java)

    fun checkEnteredData(signUpRequest: SignUpRequest) {
        viewModelScope.launch(Const.errorHandler) {
            isSuccessfulSignUp.value = true
        }
    }

    private suspend fun signUp(signUpRequest: SignUpRequest) = withContext(Dispatchers.IO) {
        client.signUp(signUpRequest)
    }
}