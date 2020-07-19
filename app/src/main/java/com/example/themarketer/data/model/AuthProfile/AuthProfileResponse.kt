package com.example.themarketer.data.model.AuthProfile


import com.google.gson.annotations.SerializedName

data class AuthProfileResponse(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)