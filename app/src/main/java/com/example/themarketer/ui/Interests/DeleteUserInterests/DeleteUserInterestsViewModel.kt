package com.example.themarketer.ui.Interests.DeleteUserInterests

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.DeleteUserInterests.DeleteUserInterestsResponse
import com.example.themarketer.data.repository.DeleteUserInterestsRepository
import com.example.themarketer.utils.Progressive
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class DeleteUserInterestsViewModel /*: ViewModel()*/ {
  /*  var disposable = CompositeDisposable()
    var progressive: Progressive? = null

    val DeleteUserInterestsLiveData: MutableLiveData<DeleteUserInterestsResponse> by lazy {
        MutableLiveData<DeleteUserInterestsResponse>()
    }


    fun loadDeleteUserInterests(categoryId: String,auth: String): LiveData<DeleteUserInterestsResponse> {
        progressive?.onStarted()
        DeleteUserInterestsRepository().deleteUserInterests(categoryId,"Bearer "+auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess {  progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<DeleteUserInterestsResponse>> {
                override fun onSuccess(t: Response<DeleteUserInterestsResponse>) {

                    DeleteUserInterestsLiveData.value = t.body()

                    Log.d("rest", "" + DeleteUserInterestsLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return DeleteUserInterestsLiveData
    }





    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }*/

}