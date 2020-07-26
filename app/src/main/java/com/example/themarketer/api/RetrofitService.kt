package com.example.themarketer.api

import com.example.themarketer.Constants
import com.example.themarketer.data.model.AddInterests.AddInterestsResponse
import com.example.themarketer.data.model.AddReview.AddReviewResponse
import com.example.themarketer.data.model.AllSection.AllSectionResponse
import com.example.themarketer.data.model.Interests.InterestsResponse
import com.example.themarketer.data.model.AuthProfile.AuthProfileResponse
import com.example.themarketer.data.model.ChangePassword.ChangePasswordResponse
import com.example.themarketer.data.model.DeleteUserInterests.DeleteUserInterestsResponse
import com.example.themarketer.data.model.EditProfile.EditProfileResponse
import com.example.themarketer.data.model.FavoriteUserCategories.FavoriteUserCategoriesResponse
import com.example.themarketer.data.model.LogOut.LogOutResponse
import com.example.themarketer.data.model.Login.LoginResponse
import com.example.themarketer.data.model.Menu.MenuSections.MenuSectionsResponse
import com.example.themarketer.data.model.Menu.MenuSlider.MenuSliderResponse
import com.example.themarketer.data.model.ProductDetails.ProductDetailsResponse
import com.example.themarketer.data.model.Register.RegisterResponse
import com.example.themarketer.data.model.ProductReviews.ProductReviewsResponse
import com.example.themarketer.data.model.Reviews.ReviewsResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitService {

    @FormUrlEncoded
    @POST("auth/login")
    fun userLogin(
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("remember_me") remember_me: String

    ): Call<LoginResponse>


    @FormUrlEncoded
    @POST("auth/signup")
    fun userRegister(
        @Field("f_name") fName: String,
        @Field("l_name") lName: String,
        @Field("username") username: String,
        @Field("phone") phone: String,
        @Field("gender") gender: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String
    ): Single<Response<RegisterResponse>>


    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("auth/user")
    fun authProfile(
        @Header("Authorization")  auth:String

    ): Single<Response<AuthProfileResponse>>


    @FormUrlEncoded
    @POST("auth/edit/profile")
    fun editProfile(
        @Field("f_name") fName: String,
        @Field("l_name") lName: String,
        @Field("username") username: String,
        @Field("phone") phone: String,
        @Field("gender") gender: String,
        @Field("email") email: String,
        @Field("address") address:String,
        @Field("image") image:String,
        @Header("Authorization")  auth:String
    ): Single<Response<EditProfileResponse>>


    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("auth/logout")
    fun logOut(
        @Header("Authorization")  auth:String

    ): Single<Response<LogOutResponse>>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("categories")
    fun getInterests(
        @Header("Authorization")  auth:String

    ): Single<Response<InterestsResponse>>

    @FormUrlEncoded
    @POST("users/categories")
    fun addInterests(
        @Field("category_ids[]") array_of_categoryId: ArrayList<Int>,
        @Header("Authorization")  auth:String): Single<Response<AddInterestsResponse>>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("sections")
    fun getMenuSections(
        @Header("Authorization")  auth:String

    ): Single<Response<MenuSectionsResponse>>



    @DELETE("users/categories/{categoryId}")
    fun deleteUserInterests(
        @Path(value = "categoryId", encoded = true) categoryId:String,
        @Header("Authorization")  auth:String

    ): Single<Response<DeleteUserInterestsResponse>>

    @FormUrlEncoded
    @POST("auth/change-password")
    fun changePassword(
        @Field("new_password") new_password: String,
        @Field("new_password_confirmation") new_password_confirmation: String,
        @Header("Authorization")  auth:String
    ): Call<ChangePasswordResponse>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("products/{product_id}")
    fun getProductDetails(@Path(value = "product_id", encoded = true) product_id:String ,
                          @Header("Authorization")  auth:String): Single<Response<ProductDetailsResponse>>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("products/{product_id}/reviews")
    fun getProductReviews(@Path(value = "product_id", encoded = true) product_id:String ,
                          @Header("Authorization")  auth:String): Single<Response<ProductReviewsResponse>>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("sections/{section_id}")
    fun getAllSection(@Path(value = "section_id", encoded = true) section_id:String ,
                          @Header("Authorization")  auth:String): Single<Response<AllSectionResponse>>

    @FormUrlEncoded
    @POST("products/{product_id}/reviews")
    fun addReview(
        @Path(value = "product_id", encoded = true) product_id:String,
        @Field("customer_name") customer_name: String,
        @Field("body") body: String,
        @Field("star") star: Int,
        @Header("Authorization")  auth:String): Call<AddReviewResponse>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("products/{product_id}/reviews")
    fun showReviews(
        @Path(value = "product_id", encoded = true) product_id:String,
        @Header("Authorization")  auth:String): Single<Response<ReviewsResponse>>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("sliders")
    fun showMenuSlider(
        @Header("Authorization")  auth:String): Single<Response<MenuSliderResponse>>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("users/categories")
    fun showFavoriteUserCategories(
        @Header("Authorization")  auth:String

    ): Single<Response<FavoriteUserCategoriesResponse>>
    companion object {
        fun getRetrofitInstance(): RetrofitService {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(RetrofitService::class.java)
        }
    }

}