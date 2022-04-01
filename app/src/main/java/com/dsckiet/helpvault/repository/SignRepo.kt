package com.dsckiet.helpvault.repository

import com.dsckiet.helpvault.dataClass.LoginResponse
import com.dsckiet.helpvault.dataClass.SignupResponse
import com.dsckiet.helpvault.dataClass.UserLogin
import com.dsckiet.helpvault.dataClass.UserSignup
import com.dsckiet.helpvault.network.LoginService
import com.dsckiet.helpvault.network.SignupService
import retrofit2.Call
import retrofit2.http.POST

class SignRepo {

    fun signup(email:String , password:String):Call<SignupResponse>{
        return SignupService.signupInstance.signup(UserSignup(email,password))
    }

}