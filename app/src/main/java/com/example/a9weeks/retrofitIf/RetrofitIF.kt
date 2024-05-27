package com.example.a9weeks.retrofitIf

import com.example.a9weeks.dataClass.BaseData
import com.example.a9weeks.dataClass.GetProfileResponseData
import com.example.a9weeks.dataClass.LoginRequestData
import com.example.a9weeks.dataClass.LoginResponseData
import com.example.a9weeks.dataClass.PostResult
import com.example.a9weeks.dataClass.SignupRequestData
import com.example.a9weeks.dataClass.SignupResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitIF {
    @GET("/week/post")
    fun getPosts() : Call<BaseData<PostResult>>

    @POST("/week/sign")
    fun signup(
        @Body
        signupRequestData : SignupRequestData
    ) : Call<BaseData<SignupResponseData>>

    @POST("/week/login")
    fun login(
        @Body
        loginRequestData: LoginRequestData
    ) : Call<BaseData<LoginResponseData>>

    @GET("/week/nick-name")
    fun getProfile(
        @Header("Authorization")
        accessToken : String
    ) : Call<BaseData<GetProfileResponseData>>
}