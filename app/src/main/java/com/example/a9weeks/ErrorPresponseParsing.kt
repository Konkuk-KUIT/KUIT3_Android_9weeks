package com.example.a9weeks

import org.json.JSONObject


fun parseErrorMessage(errorBody: String?): String {
    return try {
        val jsonObj = JSONObject(errorBody)
        jsonObj.getString("message")
    } catch (e: Exception) {
        errorBody ?: "Unknown error"
    }
}