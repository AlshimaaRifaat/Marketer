package com.example.themarketer.ui.TopMarketers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.AllSection.AllSectionResponse
import com.example.themarketer.data.repository.TopMarketersRepository
import com.example.themarketer.utils.Progressive
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class TopMarketersViewModel : ViewModel() {
    var disposable = CompositeDisposable()
    var progressive: Progressive? = null

    var disposableBrands = CompositeDisposable()

    val allMarketersSectionLiveData: MutableLiveData<AllSectionResponse> by lazy {
        MutableLiveData<AllSectionResponse>()
    }

    val allBrandsSectionLiveData: MutableLiveData<AllSectionResponse> by lazy {
        MutableLiveData<AllSectionResponse>()
    }

    fun loadAllTopMarketersSection(sectionId:String,auth: String): LiveData<AllSectionResponse> {
        progressive?.onStarted()
        TopMarketersRepository().get_AllSection(sectionId,"Bearer " + auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess { progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<AllSectionResponse>> {
                override fun onSuccess(t: Response<AllSectionResponse>) {

                    allMarketersSectionLiveData.value = t.body()

                    Log.d("rest", "" + allMarketersSectionLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return allMarketersSectionLiveData
    }

    fun loadAllPopularBrandSection(sectionId:String,auth: String): LiveData<AllSectionResponse> {
        progressive?.onStarted()
        TopMarketersRepository().get_AllSection(sectionId,"Bearer " + auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess { progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<AllSectionResponse>> {
                override fun onSuccess(t: Response<AllSectionResponse>) {

                    allBrandsSectionLiveData.value = t.body()

                    Log.d("rest", "" + allBrandsSectionLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return allBrandsSectionLiveData
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        disposableBrands

    }
}