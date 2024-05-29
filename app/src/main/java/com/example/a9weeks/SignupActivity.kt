package com.example.a9weeks


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.a9weeks.dataClass.BaseData
import com.example.a9weeks.dataClass.SignUpRequestData
import com.example.a9weeks.dataClass.SignUpResponseData

import com.example.a9weeks.databinding.ActivitySignupBinding
import com.example.a9weeks.retrofitIf.RetrofitIF
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SignupActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)

        val retrofit= RetrofitService.retrofit
        val service=retrofit.create(RetrofitIF::class.java)

        binding.btnSignup.setOnClickListener {
            val userId=binding.etId.text.toString()
            val password=binding.etPassword.text.toString()
            val nickName=binding.etNickName.text.toString()
            val intent= Intent(this, LoginActivity::class.java)

            val signUpRequestData=SignUpRequestData(userId,password, nickName)
            val signUpcall = service.postSignup(signUpRequestData)
            signUpcall.enqueue(object : Callback<BaseData<SignUpResponseData>>{
                override fun onResponse(
                    call: Call<BaseData<SignUpResponseData>>,
                    response: Response<BaseData<SignUpResponseData>>
                ) {
                    if(response.isSuccessful){
                        Log.d("상태", response.body().toString())

                        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }else{ //응답 실패하면 실패 메세지
                        val body=parseErrorMessage(response.errorBody()?.byteString()?.string(Charsets.UTF_8))
                        val toast= Toast.makeText(this@SignupActivity,body, Toast.LENGTH_LONG)
                        toast.show()
                    }
                }

                override fun onFailure(call: Call<BaseData<SignUpResponseData>>, t: Throwable) {
                    val toast= Toast.makeText(this@SignupActivity,"네트워크 연결 실패", Toast.LENGTH_LONG)
                    toast.show()
                }
            })

        }

        setContentView(binding.root)

    }

}