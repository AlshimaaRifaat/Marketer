package com.example.themarketer.data.model.Register


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)