package com.example.a9weeks.retrofitIf;


import com.example.a9weeks.dataClass.AccessToken;
import com.example.a9weeks.dataClass.BaseData;
import com.example.a9weeks.dataClass.LoginRequestData;
import com.example.a9weeks.dataClass.NickName;
import com.example.a9weeks.dataClass.PostResult;
import com.example.a9weeks.dataClass.SignupRequestData;
import com.example.a9weeks.dataClass.State;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface RetrofitIF {

   @GET("/week/post")
   Call<BaseData<PostResult>> getPost();

   @POST("/week/post")
    Call<BaseData<State>> signup(
            @Body
            SignupRequestData signupRequestData
   );

   @POST("/week/login")
   Call<BaseData<AccessToken>> logIn(
           @Body
           LoginRequestData loginRequestData
   );

   @GET("/week/nick-name")
   Call<BaseData<NickName>> getNickName(
           @Header("Authorization")
           String accessToken
   );
}
