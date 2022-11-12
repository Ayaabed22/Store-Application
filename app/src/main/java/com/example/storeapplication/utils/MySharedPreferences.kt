package com.example.storeapplication.utils

import android.content.Context
import android.content.SharedPreferences

object MySharedPreferences {

    private var mSharedPref: SharedPreferences?=null
    private const val SHARED_PREFS_FILE_NAME = "my_app_shared_prefs"
    var KEY_MY_SHARED_BOOLEAN_LOGIN = "Is Login"
    var KEY_MY_SHARED_String = "User Data"

    fun getPrefs(context: Context?): SharedPreferences? {
            if(mSharedPref == null) {
                mSharedPref = context?.getSharedPreferences(SHARED_PREFS_FILE_NAME,Context.MODE_PRIVATE)
            }
        return mSharedPref
    }

    //Save Booleans
    fun saveBooleanLogin(context: Context?, key: String?, value: Boolean) {
        getPrefs(context)?.edit()?.putBoolean(key, value)?.apply()
    }

    //Get Booleans
    fun getBooleanLogin(context: Context?, key: String?): Boolean? {
        return getPrefs(context)?.getBoolean(key,false)
    }

    //Strings
    fun saveString(context: Context?, key: String?, value: String?) {
        getPrefs(context)?.edit()?.putString(key, value)?.apply()
    }

    fun getString(context: Context?, key: String?): String? {
        return getPrefs(context)?.getString(key, "")
    }
}