package com.example.themarketer.data.model.Reviews


import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    @SerializedName("data")
    var `data`: List<ReviewsData>,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)