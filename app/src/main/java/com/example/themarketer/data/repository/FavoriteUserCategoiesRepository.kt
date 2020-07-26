package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.FavoriteUserCategories.FavoriteUserCategoriesResponse
import io.reactivex.Single
import retrofit2.Response

class FavoriteUserCategoiesRepository {
    fun showFavoriteUserCategoies(auth: String): Single<Response<FavoriteUserCategoriesResponse>> {
        return RetrofitService.getRetrofitInstance().showFavoriteUserCategories(auth)
    }
}