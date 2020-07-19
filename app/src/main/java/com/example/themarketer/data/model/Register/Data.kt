package com.example.themarketer.data.model.Register


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("expires_at")
    var expiresAt: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("token")
    var token: String,
    @SerializedName("token_type")
    var tokenType: String
)