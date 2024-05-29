package com.example.a9weeks

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a9weeks.dataClass.BaseData
import com.example.a9weeks.dataClass.NickNameResponseData
import com.example.a9weeks.databinding.FragmentProfileBinding
import com.example.a9weeks.retrofitIf.RetrofitIF
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Profile : Fragment() {
    lateinit var binding : FragmentProfileBinding
    lateinit var sharedPreferences:SharedPreferenceUtil
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        sharedPreferences= SharedPreferenceUtil(requireContext())

        val retrofit=RetrofitService.retrofit
        val service=retrofit.create(RetrofitIF::class.java)
        val accessToken=sharedPreferences.getString("accessToken","null")

        service.getNickname("Bearer $accessToken").enqueue(
            object : Callback<BaseData<NickNameResponseData>>{
                override fun onResponse(
                    call: Call<BaseData<NickNameResponseData>>,
                    response: Response<BaseData<NickNameResponseData>>
                ) {
                    if(response.isSuccessful){
                        binding.tvNickName.text=response.body()!!.result.nickName
                    }
                }

                override fun onFailure(call: Call<BaseData<NickNameResponseData>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )

        return binding.root
    }
}