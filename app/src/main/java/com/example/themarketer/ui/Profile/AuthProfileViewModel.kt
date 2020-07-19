package com.example.themarketer.ui.Profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.AuthProfile.AuthProfileResponse
import com.example.themarketer.data.repository.AuthProfileRepository
import com.example.themarketer.utils.Progressive
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class AuthProfileViewModel : ViewModel() {
    var disposable = CompositeDisposable()
    var progressive: Progressive? = null

    val authProfileLiveData: MutableLiveData<AuthProfileResponse> by lazy {
        MutableLiveData<AuthProfileResponse>()
    }


    fun loadAuthProfile(auth:String): LiveData<AuthProfileResponse> {
        progressive?.onStarted()
        AuthProfileRepository().auth_Profile("Bearer "+auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess {  progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<AuthProfileResponse>> {
                override fun onSuccess(t: Response<AuthProfileResponse>) {

                    authProfileLiveData.value = t.body()

                    Log.d("prof", "" + authProfileLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return authProfileLiveData
    }





    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}