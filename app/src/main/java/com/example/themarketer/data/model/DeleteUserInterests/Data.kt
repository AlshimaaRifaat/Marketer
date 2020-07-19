package com.example.themarketer.data.model.DeleteUserInterests


import com.example.themarketer.data.model.DeleteUserInterests.Category
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("categories")
    var categories: List<Category>,
    @SerializedName("email")
    var email: Any,
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
    var phone: String,
    @SerializedName("username")
    var username: String
)