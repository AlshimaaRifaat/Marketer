package com.example.themarketer.ui.Profile.LogOut

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.LogOut.LogOutResponse
import com.example.themarketer.data.repository.LogOutRepository
import com.example.themarketer.utils.Progressive
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class LogOutViewModel : ViewModel() {
    var disposable = CompositeDisposable()
    var progressive: Progressive? = null

    val logOutLiveData: MutableLiveData<LogOutResponse> by lazy {
        MutableLiveData<LogOutResponse>()
    }


    fun loadLogOut(auth:String): LiveData<LogOutResponse> {
        progressive?.onStarted()
        LogOutRepository().log_Out("Bearer "+auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess {  progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<LogOutResponse>> {
                override fun onSuccess(t: Response<LogOutResponse>) {

                    logOutLiveData.value = t.body()

                    Log.d("prof", "" + logOutLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return logOutLiveData
    }





    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}