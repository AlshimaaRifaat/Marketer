package com.example.themarketer.data.model.MenuSections


import com.google.gson.annotations.SerializedName

data class MenuSectionsResponse(
    @SerializedName("data")
    var `data`: List<SectionsData>,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)