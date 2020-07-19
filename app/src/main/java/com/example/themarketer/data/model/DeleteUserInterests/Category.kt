package com.example.themarketer.data.model.DeleteUserInterests


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("subcategories")
    var subcategories: String
)