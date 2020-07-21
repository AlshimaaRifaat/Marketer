package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.AddReview.AddReviewResponse
import com.example.themarketer.data.model.ProductDetails.ProductDetailsResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response

class ProducDetailsRepository {
    fun get_ProductDetails(productId: String,auth:String): Single<Response<ProductDetailsResponse>> {
        return RetrofitService.getRetrofitInstance().getProductDetails(productId,auth)
    }

    fun add_Review(productId: String,customer_name:String,body:String,star:Int,auth:String): Call<AddReviewResponse> {
        return RetrofitService.getRetrofitInstance().addReview(productId,customer_name,body,star,auth)
    }

}