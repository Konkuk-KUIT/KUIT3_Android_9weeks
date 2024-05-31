package com.example.a9weeks.retrofitIf;


import com.example.a9weeks.dataClass.BaseData;
import com.example.a9weeks.dataClass.GetProfileResponseData;
import com.example.a9weeks.dataClass.LoginRequestData;
import com.example.a9weeks.dataClass.LoginResponseData;
import com.example.a9weeks.dataClass.PostResult;
import com.example.a9weeks.dataClass.SignupRequestData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface RetrofitIF {

   @GET("/week/post")
   Call<BaseData<PostResult>> getPosts();

   @POST("/week/signup")
   Call<BaseData<SignupRequestData>> signup(@Body SignupRequestData signupRequestData);

   @POST("/week/post")
   Call<BaseData<LoginRequestData>> login(@Body LoginResponseData loginResponseData);

   @GET("/week/post")
   Call<BaseData<GetProfileResponseData>> getProfile(@Header("Authorization") String accessToken);
}
