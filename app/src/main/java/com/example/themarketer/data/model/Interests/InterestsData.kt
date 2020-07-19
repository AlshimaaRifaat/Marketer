package com.example.themarketer.data.model.Interests


import com.google.gson.annotations.SerializedName

data class InterestsData(
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("subcategories")
    var subcategories: String
)