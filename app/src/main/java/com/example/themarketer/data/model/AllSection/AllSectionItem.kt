package com.example.themarketer.data.model.AllSection


import com.google.gson.annotations.SerializedName

data class AllSectionItem(
    @SerializedName("branches")
    var branches: String,
    @SerializedName("category")
    var category: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("fee")
    var fee: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("offers")
    var offers: String,
    @SerializedName("padge")
    var padge: String,
    @SerializedName("products")
    var products: String,
    @SerializedName("section")
    var section: String
)