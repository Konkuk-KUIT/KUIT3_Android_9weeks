package com.example.a9weeks


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.a9weeks.dataClass.BaseData
import com.example.a9weeks.dataClass.LoginRequestData
import com.example.a9weeks.dataClass.LoginResponseData
import com.example.a9weeks.dataClass.SignupRequestData
import com.example.a9weeks.dataClass.SignupResponseData

import com.example.a9weeks.databinding.ActivityLoginBinding
import com.example.a9weeks.retrofitIf.RetrofitIF
import parseErrorMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = RetrofitService.retrofit
        val service = retrofit.create(RetrofitIF::class.java)

        binding.btnSignup.setOnClickListener {
            intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener{
            val userId = binding.etId.text.toString()
            val password = binding.etPassword.text.toString()
            val intent = Intent(this, LoginActivity::class.java)

            service.login(LoginRequestData(userId, password))
                .enqueue(object : Callback<BaseData<LoginResponseData>> {

                    override fun onResponse(
                        call: Call<BaseData<LoginResponseData>>,
                        response: Response<BaseData<LoginResponseData>>
                    ) {
                        if(response.isSuccessful){
                            val response = response.body()
                            if (response != null) {
                                Log.d("성공", response.toString())

                                val accessToken = response.result.accessToken

                                // SharedPreferences에 액세스 토큰 저장
                                val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                                val editor = sharedPreferences.edit()
                                editor.putString("accessToken", accessToken)
                                editor.apply()

                                // HomeFragment 띄우기
                                val homeFragment = Home()
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.main_frm, homeFragment)
                                    .addToBackStack(null)
                                    .commit()
                            }

                        }
                        else {
                            val body = parseErrorMessage(response.errorBody()?.byteString()?.string(Charsets.UTF_8))
                            val toast = Toast.makeText(this@LoginActivity, body, Toast.LENGTH_LONG)
                            toast.show()
                        }
                    }

                    override fun onFailure(call: Call<BaseData<LoginResponseData>>, t: Throwable) {
                        Log.d("실패", t.message.toString())
                    }

                })
        }

    }
}