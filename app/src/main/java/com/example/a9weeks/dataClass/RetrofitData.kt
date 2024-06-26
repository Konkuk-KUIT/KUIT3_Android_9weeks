package com.example.a9weeks.dataClass

import com.google.gson.annotations.SerializedName
import retrofit2.http.Header
import java.sql.Timestamp
import java.time.LocalDateTime

data class BaseData<T> (
    @SerializedName("code")
    val code : Int,
    @SerializedName("status")
    val status : Int,
    @SerializedName("message")
    val message : String,
    @SerializedName("result")
    val result : T
)


data class PostResult(
    @SerializedName("userProfileImage")
    val userProfileImage : String,
    @SerializedName("nickName")
    val nickName : String,
    @SerializedName("postImage")
    val postImage : String,
    @SerializedName("like")
    val like : Int,
    @SerializedName("contents")
    val contents : String
)





