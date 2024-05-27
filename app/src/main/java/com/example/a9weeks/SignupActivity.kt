package com.example.a9weeks


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.a9weeks.dataClass.BaseData
import com.example.a9weeks.dataClass.SignupRequestData
import com.example.a9weeks.dataClass.SignupResponseData

import com.example.a9weeks.databinding.ActivitySignupBinding
import com.example.a9weeks.retrofitIf.RetrofitIF
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = RetrofitService.retrofit
        val service = retrofit.create(RetrofitIF::class.java)

        binding.btnSignup.setOnClickListener{
            val userId = binding.etId.text.toString()
            val password = binding.etPassword.text.toString()
            val nickname = binding.etNickName.text.toString()
            val intent = Intent(this, LoginActivity::class.java)

            service.signup(SignupRequestData(userId, password, nickname))
                .enqueue(object : Callback<BaseData<SignupResponseData>> {

                    override fun onResponse(
                        call: Call<BaseData<SignupResponseData>>,
                        response: Response<BaseData<SignupResponseData>>
                    ) {
                        if(response.isSuccessful){
                            Log.d("성공", response.body().toString())

                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        } else {
                            val body = parseErrorMessage(response.errorBody()?.byteString()?.string(Charsets.UTF_8))
                            val toast = Toast.makeText(this@SignupActivity, body, Toast.LENGTH_LONG)
                            toast.show()
                        }
                    }

                    override fun onFailure(call: Call<BaseData<SignupResponseData>>, t: Throwable) {
                        Log.d("실패", t.message.toString())
                    }

                })
        }
    }
}