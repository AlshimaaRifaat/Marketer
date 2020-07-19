package com.example.themarketer.data.model.DeleteUserInterests


import com.example.themarketer.data.model.DeleteUserInterests.Data
import com.google.gson.annotations.SerializedName

data class DeleteUserInterestsResponse(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)