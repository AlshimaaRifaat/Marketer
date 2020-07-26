package com.example.themarketer.data.model.FavoriteUserCategories


import com.google.gson.annotations.SerializedName

data class FavoriteUserCategoriesResponse(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
) {
    data class Data(
        @SerializedName("categories")
        var categories: ArrayList<Category>,
        @SerializedName("email")
        var email: Any,
        @SerializedName("first_name")
        var firstName: String,
        @SerializedName("gender")
        var gender: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("image")
        var image: String,
        @SerializedName("last_name")
        var lastName: String,
        @SerializedName("phone")
        var phone: String,
        @SerializedName("username")
        var username: String

    ) {
        data class Category(
            @SerializedName("id")
            var id: Int,
            @SerializedName("image")
            var image: String,
            @SerializedName("name")
            var name: String,
            @SerializedName("subcategories")
            var subcategories: String
        )
    }


}