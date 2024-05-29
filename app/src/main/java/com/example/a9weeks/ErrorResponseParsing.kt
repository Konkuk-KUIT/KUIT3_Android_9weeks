package com.example.a9weeks

import android.util.Log
import com.example.a9weeks.dataClass.ErrorResponse
import com.google.gson.Gson

fun parseErrorMessage(parseData: String?):String {
    return try {
        Log.d("parseData", parseData.toString())
        val messageResponse = Gson().fromJson(parseData, ErrorResponse::class.java)
        val message = messageResponse.message ?: "UnKnown error"
        return message
    } catch(e:Exception){
        "Error parsing message"
    }
}