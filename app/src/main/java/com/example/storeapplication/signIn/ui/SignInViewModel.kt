package com.example.storeapplication.signIn.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.apiService.UserAPI
import com.example.storeapplication.cart.data.GetAllUsersResponse
import com.example.storeapplication.signIn.data.SignInRequest
import com.example.storeapplication.signIn.data.SignInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel:ViewModel() {

    private val tag = "SignInViewModel"
    val isSuccessfulAuthentication = MutableLiveData<Boolean>()
    var userData = MutableLiveData<GetAllUsersResponse>()
    private val client = RetrofitClient.getInstance()!!.create(UserAPI::class.java)


    fun checkEnteredData(userName: String, password: String) {
        val loginRequest = SignInRequest(userName, password)
        client.login(loginRequest).enqueue(object : Callback<SignInResponse> {
            override fun onResponse(
                call:Call<SignInResponse>,
                response: Response<SignInResponse>
            ) {
                if (response.isSuccessful) {
                    Log.i(tag, "onResponse: " + response.body().toString())
                    isSuccessfulAuthentication.value = true
                } else {
                    Log.i(tag, "onResponse: "+ response.errorBody())
                    isSuccessfulAuthentication.value = false
                }
            }
            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                Log.i(tag, "onFailure:  " + t.localizedMessage)
                isSuccessfulAuthentication.value = false
            }
        })
    }


    fun getUserData(userName:String){
        client.getAllUsers().enqueue(object : Callback<MutableList<GetAllUsersResponse>> {
            override fun onResponse(
                call: Call<MutableList<GetAllUsersResponse>>,
                response: Response<MutableList<GetAllUsersResponse>>
            ) {
                if (response.isSuccessful){
                    Log.i(tag, "onResponse: " + response.body().toString())
                    userData.value = response.body()?.find { it.username == userName }
                    Log.i(tag, "User Name: " + response.body()?.find { it.username == userName })
                }
            }

            override fun onFailure(call: Call<MutableList<GetAllUsersResponse>>, t: Throwable) {
                Log.i(tag, "onFailure: "+ t.localizedMessage)
            }
        })
    }
}