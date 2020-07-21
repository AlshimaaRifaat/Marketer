package com.example.themarketer.data.model.ProductReviews


import com.google.gson.annotations.SerializedName

data class ProductReviewsData(
    @SerializedName("body")
    var body: String,
    @SerializedName("customer name")
    var customerName: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("star")
    var star: String
)