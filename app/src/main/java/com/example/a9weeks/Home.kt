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


class Home : Fragment() {
    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val retrofit = RetrofitService.retrofit

        val service = retrofit.create(RetrofitIF::class.java)

        service.getPosts().enqueue(object : Callback<BaseData<PostResult>> {
            override fun onResponse(call: Call<BaseData<PostResult>>, response: Response<BaseData<PostResult>>) {
                if(response.isSuccessful){
                    val response = response.body()
                    //받아온 파일 적용하기
                    if (response != null) {
                        Glide.with(requireContext())
                            .load(response.result.postImage)
                            .into(binding.ivHomePostingImage)
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