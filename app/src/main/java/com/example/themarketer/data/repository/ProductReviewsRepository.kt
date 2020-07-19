package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.ProductReviews.ProductReviewsResponse
import io.reactivex.Single
import retrofit2.Response

class ProductReviewsRepository {
    fun get_ProductReviews(productId:String,auth: String): Single<Response<ProductReviewsResponse>> {
        return RetrofitService.getRetrofitInstance().getProductReviews(productId,auth)
    }
}