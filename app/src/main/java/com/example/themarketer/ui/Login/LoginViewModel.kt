package com.example.themarketer.ui.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.Login.LoginResponse
import com.example.themarketer.data.repository.LoginRepository
import com.example.themarketer.utils.Progressive
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class LoginViewModel:ViewModel() {
    var disposable = CompositeDisposable()
    var progressive: Progressive? = null

    val loginLiveData: MutableLiveData<LoginResponse> by lazy {
        MutableLiveData<LoginResponse>()
    }


    fun loadLogin(phone: String,password: String,remember_me :String): LiveData<LoginResponse>  {
        progressive?.onStarted()
       LoginRepository().login(phone,password,remember_me)
        .enqueue(object : Callback, retrofit2.Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                progressive?.onSuccess()

                loginLiveData?.setValue(response.body()!!)


            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
               // loginLiveData?.setValue()

            }
        })
        return loginLiveData
    }





    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}