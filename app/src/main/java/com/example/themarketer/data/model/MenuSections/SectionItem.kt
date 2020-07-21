package com.example.themarketer.data.model.MenuSections


import com.google.gson.annotations.SerializedName

data class SectionItem(
    @SerializedName("battery")
    var battery: String,
    @SerializedName("branches")
    var branches: String,
    @SerializedName("camera")
    var camera: Any,
    @SerializedName("category")
    var category: String,
    @SerializedName("color")
    var color: List<String>,
    @SerializedName("description")
    var description: String,
    @SerializedName("discount")
    var discount: Float,
    @SerializedName("fee")
    var fee: String,
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
    var memory: Any,
    @SerializedName("name")
    var name: String,
    @SerializedName("offers")
    var offers: String,
    @SerializedName("order_delivered_date")
    var orderDeliveredDate: String,
    @SerializedName("padge")
    var padge: String,
    @SerializedName("price")
    var price: List<Price>,
    @SerializedName("products")
    var products: String,
    @SerializedName("rate_as_categories")
    var rateAsCategories: RateAsCategories,
    @SerializedName("rate_as_value")
    var rateAsValue: String,
    @SerializedName("reviews")
    var reviews: String,
    @SerializedName("screen")
    var screen: Any,
    @SerializedName("section")
    var section: String,
    @SerializedName("size")
    var size: List<String>,
    @SerializedName("store")
    var store: String,
    @SerializedName("store_image")
    var storeImage: String,
    @SerializedName("subcategory")
    var subcategory: String
)