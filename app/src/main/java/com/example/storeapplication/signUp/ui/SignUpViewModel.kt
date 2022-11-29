package com.example.storeapplication.signUp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.apiService.UserAPI
import com.example.storeapplication.signUp.data.SignUpRequest
import com.example.storeapplication.signUp.data.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    val isSuccessfulSignUp = MutableLiveData<Boolean>()

    fun signUp(signUpRequest: SignUpRequest) {
        val client = RetrofitClient.getInstance()!!.create(UserAPI::class.java)
        client.signUp(signUpRequest).enqueue(object : Callback<SignUpResponse> {
                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.i(TAG, "onResponse: " + response.body().toString())
                        isSuccessfulSignUp.value = true
                    } else {
                        Log.i(TAG, "onResponse: " + response.errorBody().toString())
                        isSuccessfulSignUp.value = false
                    }
                }
                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    Log.i(TAG, "onFailure: " + t.localizedMessage)
                    isSuccessfulSignUp.value = false
                }
            })
    }
    companion object {
        private const val TAG = "SignUpViewModel"
    }
}