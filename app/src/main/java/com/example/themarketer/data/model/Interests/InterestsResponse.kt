package com.example.themarketer.data.model.Interests


import com.google.gson.annotations.SerializedName

data class InterestsResponse(
    @SerializedName("data")
    var `data`: List<InterestsData>,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)