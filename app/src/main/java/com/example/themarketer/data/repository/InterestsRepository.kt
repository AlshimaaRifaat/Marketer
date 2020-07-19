package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.Interests.InterestsResponse
import io.reactivex.Single
import retrofit2.Response

class InterestsRepository {
    fun get_Interests(auth: String): Single<Response<InterestsResponse>> {
        return RetrofitService.getRetrofitInstance().getInterests(auth)
    }
}