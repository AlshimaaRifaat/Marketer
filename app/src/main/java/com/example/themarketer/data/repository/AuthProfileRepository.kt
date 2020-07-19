package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.AuthProfile.AuthProfileResponse
import io.reactivex.Single
import retrofit2.Response

class AuthProfileRepository {

    fun auth_Profile(auth: String): Single<Response<AuthProfileResponse>> {
        return RetrofitService.getRetrofitInstance().authProfile(auth)
    }
}