package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.ProductDetails.ProductDetailsResponse
import io.reactivex.Single
import retrofit2.Response

class ProducDetailsRepository {
    fun get_ProductDetails(productId: String,auth:String): Single<Response<ProductDetailsResponse>> {
        return RetrofitService.getRetrofitInstance().getProductDetails(productId,auth)
    }

}