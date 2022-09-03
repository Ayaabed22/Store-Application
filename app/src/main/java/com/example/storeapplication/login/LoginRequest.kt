package com.example.storeapplication.login

import com.google.gson.annotations.SerializedName

class LoginRequest (userName:String , password:String){

    @SerializedName("username")
    var username: String? = null

    @SerializedName("password")
    var password: String? = null
}