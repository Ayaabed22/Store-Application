package com.example.storeapplication.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.apiService.UserAPI
import com.example.storeapplication.cart.data.GetAllUsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {

    private val client = RetrofitClient.getInstance()!!.create(UserAPI::class.java)
    val userProfileData = MutableLiveData<GetAllUsersResponse>()
    private val tag = "ProfileViewModel"

    fun getUserData(id:String) {
        client.getUserData(id).enqueue(object : Callback<GetAllUsersResponse> {
            override fun onResponse(
                call: Call<GetAllUsersResponse>,
                response: Response<GetAllUsersResponse>
            ) {
                Log.i(tag, "onResponse: " + response.body())
                userProfileData.value = response.body()
            }

            override fun onFailure(call: Call<GetAllUsersResponse>, t: Throwable) {
                Log.i(tag, "onFailure: " + t.localizedMessage)
            }
        })
    }
}