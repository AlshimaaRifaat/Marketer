package com.example.themarketer.data.model.Menu.MenuSlider


import com.google.gson.annotations.SerializedName

data class MenuSliderResponse(
    @SerializedName("data")
    var `data`: List<MenuSliderData>,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)