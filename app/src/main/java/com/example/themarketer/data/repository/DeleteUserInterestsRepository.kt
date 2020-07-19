package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.DeleteUserInterests.DeleteUserInterestsResponse
import io.reactivex.Single
import retrofit2.Response

class DeleteUserInterestsRepository {
    fun deleteUserInterests(categoryId: String,auth:String): Single<Response<DeleteUserInterestsResponse>> {
        return RetrofitService.getRetrofitInstance().deleteUserInterests(categoryId,auth)
    }
}