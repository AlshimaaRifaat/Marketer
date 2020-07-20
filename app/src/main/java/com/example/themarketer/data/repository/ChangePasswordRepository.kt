package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.ChangePassword.ChangePasswordResponse
import com.example.themarketer.data.model.Login.LoginResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response

class ChangePasswordRepository {
    fun change_Password(new_password: String,new_password_confirmation: String,auth: String): Call<ChangePasswordResponse> {
        return RetrofitService.getRetrofitInstance().changePassword(new_password,new_password_confirmation,auth)
    }
}