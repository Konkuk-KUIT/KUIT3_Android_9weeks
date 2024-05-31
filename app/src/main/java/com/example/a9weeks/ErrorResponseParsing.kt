package com.example.a9weeks

import com.example.a9weeks.dataClass.ErrorResponse
import com.google.gson.Gson

fun parseErrorMessage(parseData: String?): String {
    val messageResponse = Gson().fromJson(parseData, ErrorResponse::class.java)
    val message = messageResponse.message
    return message
}