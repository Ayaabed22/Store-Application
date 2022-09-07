package com.example.storeapplication.signUp

import com.google.gson.annotations.SerializedName

data class SignUpResponse(@SerializedName("address"  ) var address  : Address? = Address(),
                          @SerializedName("_id"      ) var Id       : String?  = null,
                          @SerializedName("id"       ) var id       : Int?     = null,
                          @SerializedName("email"    ) var email    : String?  = null,
                          @SerializedName("username" ) var username : String?  = null,
                          @SerializedName("password" ) var password : String?  = null,
                          @SerializedName("phone"    ) var phone    : String?  = null)


