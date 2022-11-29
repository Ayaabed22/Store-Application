package com.example.storeapplication.signIn.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.apiService.UserAPI
import com.example.storeapplication.cart.data.GetAllUsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataViewModel:ViewModel() {

    var userData = MutableLiveData<GetAllUsersResponse>()

    fun getUserData(userName:String){
        val client = RetrofitClient.getInstance()!!.create(UserAPI::class.java)
        client.getAllUsers().enqueue(object : Callback<MutableList<GetAllUsersResponse>> {
            override fun onResponse(
                call: Call<MutableList<GetAllUsersResponse>>,
                response: Response<MutableList<GetAllUsersResponse>>
            ) {
                if (response.isSuccessful){
                    Log.i(TAG, "onResponse: " + response.body().toString())
                    userData.value = response.body()?.find { it.username == userName }
                    Log.i(TAG, "User Name: " + response.body()?.find { it.username == userName })
                }
            }

            override fun onFailure(call: Call<MutableList<GetAllUsersResponse>>, t: Throwable) {
                Log.i(TAG, "onFailure: "+ t.localizedMessage)
            }

        })
    }
    companion object {
        private const val  TAG = "SignInViewModel"
    }
}