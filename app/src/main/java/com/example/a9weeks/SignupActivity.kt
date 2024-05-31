package com.example.a9weeks


import android.content.Intent
import android.content.SharedPreferences
import android.media.session.MediaSession.Token
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.a9weeks.dataClass.BaseData
import com.example.a9weeks.dataClass.ErrorResponse
import com.example.a9weeks.dataClass.SignupRequestData
import com.example.a9weeks.dataClass.State
import com.example.a9weeks.databinding.ActivityLoginBinding

import com.example.a9weeks.databinding.ActivitySignupBinding
import com.example.a9weeks.ui.theme.SharedPreferenceUtil
import retrofit2.create
import com.example.a9weeks.retrofitIf.RetrofitIF
import com.google.gson.Gson
import okio.Utf8
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset

class SignupActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignupBinding
    companion object {
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)

        val retrofit = RetrofitService.retrofit
        val service = retrofit.create(RetrofitIF::class.java)

        binding.btnSignup.setOnClickListener {
            var userId = binding.etId.toString()
            var password = binding.etPassword.toString()
            var nickName = binding.etNickName.toString()
            var intent = Intent(this, LoginActivity::class.java)

            service.signup(SignupRequestData(userId, password, nickName))
                .enqueue(object : Callback<BaseData<State>>{
                    override fun onResponse(
                        call: Call<BaseData<State>>,
                        response: Response<BaseData<State>>
                    ) {
                        if(response.isSuccessful){
                            Log.d("상태", response.body().toString())

                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                        else{
                            val bodystring = response.errorBody()?.byteString()?.string(Charsets.UTF_8)
                            val body = Gson().fromJson(bodystring,ErrorResponse::class.java).message
                            val toast = Toast.makeText(this@SignupActivity,body,Toast.LENGTH_LONG)
                            toast.show()
                        }
                    }

                    override fun onFailure(call: Call<BaseData<State>>, t: Throwable) {
                        Log.d("상태", t.message.toString())                }
                })
        }

        setContentView(binding.root)

    }
}