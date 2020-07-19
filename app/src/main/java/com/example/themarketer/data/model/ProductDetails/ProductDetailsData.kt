package com.example.themarketer.data.model.ProductDetails


import com.google.gson.annotations.SerializedName

public data class ProductDetailsData(
    @SerializedName("battery")
    var battery: String,
    @SerializedName("camera")
    var camera: String,
    @SerializedName("category")
    var category: String,
    @SerializedName("color")
    var color: List<String>,
    @SerializedName("description")
    var description: String,
    @SerializedName("discount")
    var discount: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: String,
    @SerializedName("in offer")
    var inOffer: Boolean,
    @SerializedName("memory")
    var memory: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("padge")
    var padge: String,
    @SerializedName("price")
    var price: List<Price>,
    @SerializedName("rating")
    var rating: String,
    @SerializedName("reviews")
    var reviews: String,
    @SerializedName("screen")
    var screen: String,
    @SerializedName("size")
    var size: List<String>,
    @SerializedName("store")
    var store: String,
    @SerializedName("subcategory")
    var subcategory: String
)