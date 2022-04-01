package com.dsckiet.helpvault.network

import com.dsckiet.helpvault.dataClass.LoginResponse
import com.dsckiet.helpvault.dataClass.SignupResponse
import com.dsckiet.helpvault.dataClass.UserLogin
import com.dsckiet.helpvault.dataClass.UserSignup
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface SignInterface {

    @POST("signup")
    fun signup(@Body response : UserSignup): Call<SignupResponse>
}

object SignupService{
    val signupInstance : SignInterface

    init {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        signupInstance = retrofit.create(SignInterface::class.java)
    }

}