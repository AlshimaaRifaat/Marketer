package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.Register.RegisterResponse
import io.reactivex.Single
import retrofit2.Response

class RegisterRepository {
    fun register(fName: String,lName: String,username :String,phone: String,gender: String,password: String,password_confirmation: String): Single<Response<RegisterResponse>> {
        return RetrofitService.getRetrofitInstance().userRegister(fName,lName,username,phone,gender,password,password_confirmation)
    }
}