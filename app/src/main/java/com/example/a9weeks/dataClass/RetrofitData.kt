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

data class ErrorResponse(
    @SerializedName("code")
    val code : Int,
    @SerializedName("status")
    val status : Int,
    @SerializedName("message")
    val message : String,
    @SerializedName("timestamp")
    val timestamp : String
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

data class SignupRequestData(
    @SerializedName("userId")
    val userId : String,
    @SerializedName("password")
    val password : String,
    @SerializedName("nickName")
    val nickName : String,
)

data class SignupResponseData(
    @SerializedName("state")
    val state : String
)

data class LoginRequestData(
    @SerializedName("userId")
    val userId : String,
    @SerializedName("password")
    val password : String
)

data class LoginResponseData(
    @SerializedName("accessToken")
    val accessToken : String
)


data class GetProfileResponseData(
    @SerializedName("nickName")
    val nickName : String
)




