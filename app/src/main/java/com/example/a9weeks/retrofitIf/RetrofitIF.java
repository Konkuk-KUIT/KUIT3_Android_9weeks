package com.example.a9weeks.retrofitIf;


import com.example.a9weeks.dataClass.BaseData;
import com.example.a9weeks.dataClass.PostResult;
import retrofit2.Call;
import retrofit2.http.GET;


public interface RetrofitIF {

   @GET("/week/post")
   Call<BaseData<PostResult>> getPosts();

   @

}
