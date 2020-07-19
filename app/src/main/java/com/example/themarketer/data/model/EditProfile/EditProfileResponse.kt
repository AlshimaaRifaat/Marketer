package com.example.themarketer.data.model.EditProfile


import com.google.gson.annotations.SerializedName

data class EditProfileResponse(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)