package com.example.storeapplication.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapplication.apiService.RetrofitClient
import com.example.storeapplication.apiService.UserAPI
import com.example.storeapplication.cart.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel : ViewModel() {

    private val client = RetrofitClient.getInstance()!!.create(UserAPI::class.java)
    val userProfileData = MutableLiveData<User>()

    fun getUserData(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val profileData = client.getUserData(id)
            withContext(Dispatchers.Main) {
                userProfileData.value = profileData
            }
        }
    }
}