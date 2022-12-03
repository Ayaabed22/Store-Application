package com.example.storeapplication.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.storeapplication.cart.data.GetAllUsersResponse
import com.google.gson.Gson

object MySharedPreferences {

    private var mSharedPref: SharedPreferences?=null
    private const val SHARED_PREFS_FILE_NAME = "my_app_shared_prefs"
    var KEY_MY_SHARED_BOOLEAN_LOGIN = "Is Login" /*TODO: we usually don't have spaces in keys, also they should be immutable, use val*/
    var KEY_MY_SHARED_String = "User Data"

    fun getPrefs(context: Context?): SharedPreferences? {
            if(mSharedPref == null) {
                mSharedPref = context?.getSharedPreferences(SHARED_PREFS_FILE_NAME,Context.MODE_PRIVATE)
            }
        return mSharedPref
    }
/*TODO: Remove comments when they aren't adding extra meaning*/
    //Save Booleans
    fun saveBoolean(context: Context?, key: String?, value: Boolean) {
        getPrefs(context)?.edit()?.putBoolean(key, value)?.apply()
    }

    //Get Booleans
    fun getBoolean(context: Context?, key: String?): Boolean? {
        return getPrefs(context)?.getBoolean(key,false)
    }

    //Strings
    fun saveString(context: Context?, key: String?, value: String?) {
        getPrefs(context)?.edit()?.putString(key, value)?.apply()
    }

    fun getString(context: Context?, key: String?): String? {
        return getPrefs(context)?.getString(key, "")
    }

    fun getUserDataFromShared(context: Context?): GetAllUsersResponse {
        val gson = Gson()
        val json = getString(context, KEY_MY_SHARED_String)
        return gson.fromJson(json, GetAllUsersResponse::class.java)
    }
}