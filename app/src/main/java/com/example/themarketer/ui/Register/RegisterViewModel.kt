package com.example.themarketer.ui.Register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.Register.RegisterResponse
import com.example.themarketer.data.repository.RegisterRepository
import com.example.themarketer.utils.Progressive
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class RegisterViewModel : ViewModel()  {
    var disposable = CompositeDisposable()
    var progressive: Progressive? = null

    val registerLiveData: MutableLiveData<RegisterResponse> by lazy {
        MutableLiveData<RegisterResponse>()
    }


    fun loadRegister(fName: String,lName:String,username:String,phone: String,gender :String,password:String,password_confirmation:String): LiveData<RegisterResponse> {
        progressive?.onStarted()
        RegisterRepository().register(fName,lName,username,phone,gender,password,password_confirmation)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess {  progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<RegisterResponse>> {
                override fun onSuccess(t: Response<RegisterResponse>) {

                    registerLiveData.value = t.body()

                    Log.d("reg", "" + registerLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return registerLiveData
    }





    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}