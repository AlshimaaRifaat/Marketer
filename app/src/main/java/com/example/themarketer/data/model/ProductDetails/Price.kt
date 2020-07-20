package com.example.themarketer.data.model.ProductDetails


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("price")
    var price: Int,
    @SerializedName("price_after_diccount")
    var priceAfterDiccount: Int,
    @SerializedName("type")
    var type: String
)