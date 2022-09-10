package com.example.storeapplication.login.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.UserAPI
import com.example.storeapplication.login.data.LoginRequest
import com.example.storeapplication.login.data.LoginResponse
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel: ViewModel() {

    private val TAG = "SignIn"

    val isSuccessfulAuthentication = MutableLiveData<Boolean>()

    fun checkEnteredData(userName: String, password: String) {
        val client = RetrofitClient.getInstance()!!.create(UserAPI::class.java)
        val loginRequest = LoginRequest(userName, password)
        client.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: retrofit2.Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, "onResponse: " + response.body().toString())
                    Log.i(TAG, "onResponse: "+ response.errorBody())
                    isSuccessfulAuthentication.value = true
                } else {
                    Log.i(TAG, "onResponse: "+ response.errorBody())
                    isSuccessfulAuthentication.value = false
                }
            }
            override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                Log.i(TAG, "onFailure:  " + t.localizedMessage)
                isSuccessfulAuthentication.value = false
            }
        })
    }
}