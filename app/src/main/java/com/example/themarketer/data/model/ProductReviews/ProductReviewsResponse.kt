package com.example.themarketer.data.model.ProductReviews


import com.google.gson.annotations.SerializedName

data class ProductReviewsResponse(
    @SerializedName("data")
    var `data`: List<ReviewsData>,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)