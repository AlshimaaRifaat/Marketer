package com.example.themarketer.data.model.ProductDetails


import com.google.gson.annotations.SerializedName

data class ProductDetailsResponse(
    @SerializedName("data")
    var `data`: ProductDetailsData,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)