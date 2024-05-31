package com.example.a9weeks

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtil(context : Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String):String{
        return preferences.getString(key,defValue).toString()
    }

    fun setString(key: String, defValue: String){
        preferences.edit().putString(key, defValue).apply()
    }
}