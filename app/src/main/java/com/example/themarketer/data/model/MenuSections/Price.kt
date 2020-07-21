package com.example.themarketer.data.model.MenuSections


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("price")
    var price: Float,
    @SerializedName("price_after_diccount")
    var priceAfterDiccount: Float,
    @SerializedName("type")
    var type: String
)