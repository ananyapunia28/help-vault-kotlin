package com.dsckiet.helpvault.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dsckiet.helpvault.dataClass.SignupResponse
import com.dsckiet.helpvault.repository.SignRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel : ViewModel() {
    private val repoInstance = SignRepo()
    lateinit var signupResponse : Call<SignupResponse>
    val signup = MutableLiveData<SignupResponse>()

    fun signup(email:String , password:String):MutableLiveData<SignupResponse>{
        this.signupResponse = repoInstance.signup(email, password)
        signupResponse.enqueue(object : Callback<SignupResponse> {
            override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                signup.value =  response.body()
                Log.d("response", response.body().toString())
            }
            @SuppressLint("LogNotTimber")
            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                Log.d("batao", "Error in fetching: $t")
            }

        })
        return signup
    }
}