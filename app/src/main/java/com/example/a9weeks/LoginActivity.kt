package com.example.a9weeks


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.a9weeks.dataClass.BaseData
import com.example.a9weeks.dataClass.LogInRequestData
import com.example.a9weeks.dataClass.LogInResponseData
import com.example.a9weeks.dataClass.SignUpRequestData

import com.example.a9weeks.databinding.ActivityLoginBinding
import com.example.a9weeks.retrofitIf.RetrofitIF
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var sharedPreferences:SharedPreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        sharedPreferences= SharedPreferenceUtil(applicationContext)


        binding.btnSignup.setOnClickListener {
            var intent=Intent(applicationContext,SignupActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val retrofit= RetrofitService.retrofit
            val service=retrofit.create(RetrofitIF::class.java)

            val userId=binding.etId.text.toString()
            val password=binding.etPassword.text.toString()
            val intent2= Intent(this, MainActivity::class.java)

            val logInRequestData= LogInRequestData(userId,password)
            val logInCall = service.postLogIn(logInRequestData)

            logInCall.enqueue(object:Callback<BaseData<LogInResponseData>>{
                override fun onResponse(
                    call: Call<BaseData<LogInResponseData>>,
                    response: Response<BaseData<LogInResponseData>>
                ) {
                    if(response.isSuccessful){
                        val accessToken=response.body()?.result!!.accessToken
                        sharedPreferences.setString("accessToken",accessToken)
                        intent2.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent2)
                    }else{ //응답 실패하면 실패 메세지
                        val body=parseErrorMessage(response.errorBody()?.byteString()?.string(Charsets.UTF_8))
                        val toast= Toast.makeText(this@LoginActivity,body, Toast.LENGTH_LONG)
                        toast.show()
                    }
                }

                override fun onFailure(call: Call<BaseData<LogInResponseData>>, t: Throwable) {
                    val toast= Toast.makeText(this@LoginActivity,"네트워크 연결 실패", Toast.LENGTH_LONG)
                    toast.show()
                }

            })
        }

        setContentView(binding.root)

    }
}