package com.example.themarketer.data.model.AddReview


import com.google.gson.annotations.SerializedName

data class AddReviewResponse(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)