package com.example.storeapplication.signUp

import com.google.gson.annotations.SerializedName

data class SignUpRequest(@SerializedName("email"    ) var email    : String?  = null,
                         @SerializedName("username" ) var username : String?  = null,
                         @SerializedName("password" ) var password : String?  = null,
                         @SerializedName("name"     ) var name     : Name?    = Name(),
                         @SerializedName("address"  ) var address  : Address? = Address(),
                         @SerializedName("phone"    ) var phone    : String?  = null
)

data class Name(

    @SerializedName("firstname" ) var firstname : String? = null,
    @SerializedName("lastname"  ) var lastname  : String? = null

)
data class Address (

    @SerializedName("geolocation" ) var geolocation : Geolocation? = Geolocation(),
    @SerializedName("city"        ) var city        : String?      = null,
    @SerializedName("street"      ) var street      : String?      = null,
    @SerializedName("number"      ) var number      : Int?         = null,
    @SerializedName("zipcode"     ) var zipcode     : String?      = null

)

data class Geolocation (

    @SerializedName("lat"  ) var lat  : String? = null,
    @SerializedName("long" ) var long : String? = null

)