package com.example.a9weeks


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.a9weeks.dataClass.AccessToken
import com.example.a9weeks.dataClass.BaseData
import com.example.a9weeks.dataClass.ErrorResponse
import com.example.a9weeks.dataClass.LoginRequestData
import com.example.a9weeks.dataClass.SignupRequestData
import com.example.a9weeks.dataClass.State

import com.example.a9weeks.databinding.ActivityLoginBinding
import com.example.a9weeks.retrofitIf.RetrofitIF
import com.example.a9weeks.ui.theme.SharedPreferenceUtil
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var sharedPreferences : SharedPreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignup.setOnClickListener{
            var intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
        sharedPreferences = SharedPreferenceUtil(this.applicationContext)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.34.69.27")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitIF::class.java)
        val accessToken = sharedPreferences.getString("accessToken","null")

        binding.btnLogin.setOnClickListener{
            var userId = binding.etId.toString()
            var password = binding.etPassword.toString()
            var intent = Intent(this, MainActivity::class.java)

            service.logIn(LoginRequestData(userId,password))
                .enqueue(object : Callback<BaseData<AccessToken>> {
                    override fun onResponse(
                        call: Call<BaseData<AccessToken>>,
                        response: Response<BaseData<AccessToken>>
                    ) {
                        if(response.isSuccessful){
                            Log.d("상태", response.body().toString())

                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                        else{
                            val bodystring = response.errorBody()?.byteString()?.string(Charsets.UTF_8)
                            val body = Gson().fromJson(bodystring, ErrorResponse::class.java).message
                            val toast = Toast.makeText(this@LoginActivity,body, Toast.LENGTH_LONG)
                            toast.show()
                        }
                    }

                    override fun onFailure(call: Call<BaseData<AccessToken>>, t: Throwable) {
                        Log.d("상태", t.message.toString())                }
                })
        }
    }
}