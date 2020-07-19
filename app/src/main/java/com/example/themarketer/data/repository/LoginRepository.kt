package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.Login.LoginResponse
import retrofit2.Call

class LoginRepository {

    fun login(phone: String,password: String,remember_me: String): Call<LoginResponse> {
        return RetrofitService.getRetrofitInstance().userLogin(phone,password,remember_me)
    }
}