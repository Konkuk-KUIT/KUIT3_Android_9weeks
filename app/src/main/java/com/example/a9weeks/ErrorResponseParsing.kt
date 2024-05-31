package com.example.a9weeks

import android.util.Log
import com.example.a9weeks.dataClass.ErrorResponse
import com.google.gson.Gson


fun parseErrorMessage(parseData : String?): String {
    // 주어진 JSON 문자열
    Log.d("parseData", parseData.toString())
    val messageResponse = Gson().fromJson(parseData, ErrorResponse::class.java)
    val message = messageResponse.message
    return message
}