package com.example.themarketer.data.model.Login


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("expires_at")
    var expiresAt: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("token")
    var token: String,
    @SerializedName("token_type")
    var tokenType: String,
    @SerializedName("username")
    var username: String
)