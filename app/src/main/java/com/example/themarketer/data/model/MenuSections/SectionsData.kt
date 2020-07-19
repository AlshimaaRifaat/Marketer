package com.example.themarketer.data.model.MenuSections


import com.google.gson.annotations.SerializedName

data class SectionsData(
    @SerializedName("design_type")
    var designType: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("items")
    var items: List<SectionItem>,
    @SerializedName("name")
    var name: String,
    @SerializedName("type")
    var type: String
)