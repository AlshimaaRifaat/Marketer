package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.LogOut.LogOutResponse
import io.reactivex.Single
import retrofit2.Response

class LogOutRepository {
    fun log_Out(auth: String): Single<Response<LogOutResponse>> {
        return RetrofitService.getRetrofitInstance().logOut(auth)
    }
}