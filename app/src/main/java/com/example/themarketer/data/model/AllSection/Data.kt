package com.example.themarketer.data.model.AllSection


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("design_type")
    var designType: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("items")
    var items: List<AllSectionItem>,
    @SerializedName("name")
    var name: String,
    @SerializedName("type")
    var type: String
)