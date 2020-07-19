package com.example.themarketer.data.repository

import com.example.themarketer.api.RetrofitService
import com.example.themarketer.data.model.EditProfile.EditProfileResponse
import io.reactivex.Single
import retrofit2.Response

class EditProfileRepository {

    fun edit_Profile(fName: String,lName:String,username:String,phone: String,gender: String,email: String,address: String,image:String,auth:String): Single<Response<EditProfileResponse>> {
        return RetrofitService.getRetrofitInstance().editProfile(fName,lName,username,phone,gender,email,address,image,auth)
    }
}