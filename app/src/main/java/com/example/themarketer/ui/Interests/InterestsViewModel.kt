package com.example.themarketer.ui.Interests

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.AddInterests.AddInterestsResponse
import com.example.themarketer.data.model.DeleteUserInterests.DeleteUserInterestsResponse
import com.example.themarketer.data.model.Interests.InterestsResponse
import com.example.themarketer.data.repository.AddInterestsRepository
import com.example.themarketer.data.repository.DeleteUserInterestsRepository

import com.example.themarketer.data.repository.InterestsRepository

import com.example.themarketer.utils.Progressive
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class InterestsViewModel :ViewModel() {
    var disposable = CompositeDisposable()
    var progressive: Progressive? = null

    var compositeDisposable = CompositeDisposable()
    var deleteUserInterestsdisposable = CompositeDisposable()


    val interestsLiveData: MutableLiveData<InterestsResponse> by lazy {
        MutableLiveData<InterestsResponse>()
    }

    val addInterestsLiveData: MutableLiveData<AddInterestsResponse> by lazy {
        MutableLiveData<AddInterestsResponse>()
    }
    val DeleteUserInterestsLiveData: MutableLiveData<DeleteUserInterestsResponse> by lazy {
        MutableLiveData<DeleteUserInterestsResponse>()
    }
    fun loadInterests(auth:String): LiveData<InterestsResponse>  {
        progressive?.onStarted()
        InterestsRepository().get_Interests("Bearer "+auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess {  progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<InterestsResponse>> {
                override fun onSuccess(t: Response<InterestsResponse>) {

                    interestsLiveData.value = t.body()

                    Log.d("rest", "" + interestsLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return interestsLiveData
    }





    fun loadAddInterests(array_of_categoryId: ArrayList<Int>,auth:String): LiveData<AddInterestsResponse> {
        progressive?.onStarted()
        AddInterestsRepository().add_Interests(array_of_categoryId,"Bearer "+auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess {  progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<AddInterestsResponse>> {
                override fun onSuccess(t: Response<AddInterestsResponse>) {

                    addInterestsLiveData.value = t.body();
                    Log.d("rest", "" + addInterestsLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return addInterestsLiveData
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
                    deleteUserInterestsdisposable.add(d)
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
        compositeDisposable.clear()
        deleteUserInterestsdisposable.clear()
    }

}