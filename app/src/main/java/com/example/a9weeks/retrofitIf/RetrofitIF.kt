package com.example.a9weeks.retrofitIf;


import com.example.a9weeks.dataClass.BaseData;
import com.example.a9weeks.dataClass.LogInRequestData;
import com.example.a9weeks.dataClass.LogInResponseData;
import com.example.a9weeks.dataClass.NickNameResponseData;
import com.example.a9weeks.dataClass.PostResult;
import com.example.a9weeks.dataClass.SignUpRequestData;
import com.example.a9weeks.dataClass.SignUpResponseData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface RetrofitIF {

   @GET("/week/post")
   fun getPosts() : Call<BaseData<PostResult>>

   @POST("/week/sign")
   fun postSignup(
      @Body
      signUpRequestData : SignUpRequestData
   ): Call<BaseData<SignUpResponseData>>

   @POST("/week/login")
   fun postLogIn(
      @Body
      logInRequestData : LogInRequestData) :Call<BaseData<LogInResponseData>>

   @GET("/week/nick-name")
   fun getNickname(
      @Header("Authorization")
      accessToken:String) :Call<BaseData<NickNameResponseData>>

}
