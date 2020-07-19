package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.AddInterests.AddInterestsResponse
import io.reactivex.Single
import retrofit2.Response

class AddInterestsRepository {
    fun add_Interests(array_of_categoryId: ArrayList<Int>,auth:String): Single<Response<AddInterestsResponse>> {
        return RetrofitService.getRetrofitInstance().addInterests(array_of_categoryId,auth)
    }
}