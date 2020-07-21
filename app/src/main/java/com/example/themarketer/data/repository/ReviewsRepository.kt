package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.Interests.InterestsResponse
import com.example.themarketer.data.model.Reviews.ReviewsResponse
import io.reactivex.Single
import retrofit2.Response

class ReviewsRepository {
    fun show_Reviews(product_id:String,auth: String): Single<Response<ReviewsResponse>> {
        return RetrofitService.getRetrofitInstance().showReviews(product_id,auth)
    }
}