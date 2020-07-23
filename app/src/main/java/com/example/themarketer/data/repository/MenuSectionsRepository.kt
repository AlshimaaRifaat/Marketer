package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.Menu.MenuSections.MenuSectionsResponse
import io.reactivex.Single
import retrofit2.Response

class MenuSectionsRepository {
    fun get_MenuSections(auth: String): Single<Response<MenuSectionsResponse>> {
        return RetrofitService.getRetrofitInstance().getMenuSections(auth)
    }
}