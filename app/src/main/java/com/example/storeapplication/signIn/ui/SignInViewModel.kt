package com.example.storeapplication.signIn.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.UserAPI
import com.example.storeapplication.signIn.data.SignInRequest
import com.example.storeapplication.signIn.data.SignInResponse
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel:ViewModel() {

    val isSuccessfulAuthentication = MutableLiveData<Boolean>()

    fun checkEnteredData(userName: String, password: String) {
        val client = RetrofitClient.getInstance()!!.create(UserAPI::class.java)
        val loginRequest = SignInRequest(userName, password)
        client.login(loginRequest).enqueue(object : Callback<SignInResponse> {
            override fun onResponse(
                call: retrofit2.Call<SignInResponse>,
                response: Response<SignInResponse>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, "onResponse: " + response.body().toString())
                    isSuccessfulAuthentication.value = true
                } else {
                    Log.i(TAG, "onResponse: "+ response.errorBody())
                    isSuccessfulAuthentication.value = false
                }
            }
            override fun onFailure(call: retrofit2.Call<SignInResponse>, t: Throwable) {
                Log.i(TAG, "onFailure:  " + t.localizedMessage)
                isSuccessfulAuthentication.value = false
            }
        })
    }

    companion object {
        private const val TAG = "SignInViewModel"
    }
}