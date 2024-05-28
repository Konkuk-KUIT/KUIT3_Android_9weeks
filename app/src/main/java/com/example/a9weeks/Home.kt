package com.example.a9weeks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.a9weeks.dataClass.BaseData
import com.example.a9weeks.dataClass.PostResult
import com.example.a9weeks.databinding.FragmentHomeBinding
import com.example.a9weeks.retrofitIf.RetrofitIF
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class Home : Fragment() {
    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val retrofit = Retrofit.Builder().baseUrl("http://3.34.69.27")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(RetrofitIF::class.java)

        service.getPosts().enqueue(object : Callback<BaseData<PostResult>> {
            override fun onResponse(call: Call<BaseData<PostResult>>, response: Response<BaseData<PostResult>>) {
                if (response.isSuccessful){
                    val response = response.body()
                    if (response != null) {
                        Glide.with(requireContext())
                            .load(response.result.postImage)
                            .into(binding.ivHomePostingImage)

                        Glide.with(requireContext())
                            .load(response.result.userProfileImage)
                            .into(binding.sivHomePostingProfile)

                        binding.tvHomePostingProfileName.text = response.result.nickName
                        binding.tvHomePostingContentName.text = response.result.nickName
                        binding.tvHomePostingContent.text = response.result.contents
                    }

                    if (response != null) {
                        Log.d("성공", response.result.postImage)
                    }
                }
            }

            override fun onFailure(call: Call<BaseData<PostResult>>, t: Throwable) {
                Log.d("실패", t.message.toString())
            }

        })
        return binding.root
    }

}