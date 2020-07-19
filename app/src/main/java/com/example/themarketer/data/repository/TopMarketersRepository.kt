package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.AllSection.AllSectionResponse
import io.reactivex.Single
import retrofit2.Response

class TopMarketersRepository {
    fun get_AllSection(sectionId :String,auth: String): Single<Response<AllSectionResponse>> {
        return RetrofitService.getRetrofitInstance().getAllSection(sectionId,auth)
    }
}