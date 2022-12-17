package com.example.storeapplication.signUp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapplication.apiService.RetrofitClient
import com.example.storeapplication.apiService.UserAPI
import com.example.storeapplication.signUp.data.SignUpRequest
import com.example.storeapplication.utils.ErrorHandler.errorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel : ViewModel() {
    val isSuccessfulSignUp = MutableLiveData<Boolean>()
    private val client = RetrofitClient.getInstance()!!.create(UserAPI::class.java)

    fun checkEnteredData(signUpRequest: SignUpRequest) {
        viewModelScope.launch(errorHandler) {
            isSuccessfulSignUp.value = true
        }
    }

    private suspend fun signUp(signUpRequest: SignUpRequest) = withContext(Dispatchers.IO) {
        client.signUp(signUpRequest)
    }
}