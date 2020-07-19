package com.example.themarketer.data.model.ChangePassword


import com.google.gson.annotations.SerializedName

data class ChangePasswordResponse(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)