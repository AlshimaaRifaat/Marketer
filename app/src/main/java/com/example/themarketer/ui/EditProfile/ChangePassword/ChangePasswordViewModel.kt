package com.example.themarketer.ui.EditProfile.ChangePassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.ChangePassword.ChangePasswordResponse
import com.example.themarketer.data.repository.ChangePasswordRepository
import com.example.themarketer.utils.Progressive
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ChangePasswordViewModel : ViewModel() {
    var progressive: Progressive? = null

    val changePasswordLiveData: MutableLiveData<ChangePasswordResponse> by lazy {
        MutableLiveData<ChangePasswordResponse>()
    }


    fun loadChangePassword(new_password: String,new_password_confirmation: String,auth:String):
            LiveData<ChangePasswordResponse> {
        progressive?.onStarted()
        ChangePasswordRepository().change_Password(new_password,new_password_confirmation,"Bearer "+auth)
            .enqueue(object : Callback, retrofit2.Callback<ChangePasswordResponse> {
                override fun onResponse(call: Call<ChangePasswordResponse>, response: Response<ChangePasswordResponse>) {
                    progressive?.onSuccess()
                    if(response.isSuccessful) {
                        changePasswordLiveData?.setValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {
                    // loginLiveData?.setValue()


                }
            })
        return changePasswordLiveData
    }


}