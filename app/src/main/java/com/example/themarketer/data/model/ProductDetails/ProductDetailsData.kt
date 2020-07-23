package com.example.themarketer.data.model.ProductDetails


import com.google.gson.annotations.SerializedName

data class ProductDetailsData(
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
    @SerializedName("in_offer")
    var inOffer: Boolean,
    @SerializedName("in_stock")
    var inStock: String,
    @SerializedName("is_favourite")
    var isFavourite: Boolean,
    @SerializedName("memory")
    var memory: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("order_delivered_date")
    var orderDeliveredDate: String,
    @SerializedName("padge")
    var padge: String,
    @SerializedName("price")
    var price: List<Price>,
    @SerializedName("rate_as_categories")
    var rateAsCategories: RateAsCategories,
    @SerializedName("rate_as_value")
    var rateAsValue: Double,
    @SerializedName("reviews")
    var reviews: String,
    @SerializedName("screen")
    var screen: String,
    @SerializedName("size")
    var size: List<String>,
    @SerializedName("store")
    var store: String,
    @SerializedName("store_image")
    var storeImage: String,
    @SerializedName("subcategory")
    var subcategory: String
)