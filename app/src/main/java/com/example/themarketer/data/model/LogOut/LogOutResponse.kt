package com.example.themarketer.data.model.LogOut


import com.google.gson.annotations.SerializedName

data class LogOutResponse(
    @SerializedName("data")
    var `data`: Any,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)