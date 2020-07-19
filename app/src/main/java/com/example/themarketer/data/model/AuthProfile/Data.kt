package com.example.themarketer.data.model.AuthProfile


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("address")
    var address: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: String,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("phone")
    var phone: String
)