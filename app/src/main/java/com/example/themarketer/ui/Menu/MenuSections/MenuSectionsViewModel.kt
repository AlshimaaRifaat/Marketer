package com.example.themarketer.ui.Menu.MenuSections

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.Menu.MenuSections.MenuSectionsResponse
import com.example.themarketer.data.model.Menu.MenuSlider.MenuSliderResponse
import com.example.themarketer.data.repository.MenuSectionsRepository
import com.example.themarketer.utils.Progressive
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MenuSectionsViewModel : ViewModel() {
    var disposable = CompositeDisposable()
    var disposableSlider = CompositeDisposable()
    var progressive: Progressive? = null
    private val menuSectionsRepository = MenuSectionsRepository()
    private val menuSliderRepository = MenuSectionsRepository()
    val menuSectionsLiveData: MutableLiveData<MenuSectionsResponse> by lazy {
        MutableLiveData<MenuSectionsResponse>()
    }

    val menuSliderLiveData: MutableLiveData<MenuSliderResponse> by lazy {
        MutableLiveData<MenuSliderResponse>()
    }
    fun loadMenuSections(auth:String): LiveData<MenuSectionsResponse> {
        progressive?.onStarted()
        menuSectionsRepository.get_MenuSections("Bearer "+auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess {  progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<MenuSectionsResponse>> {
                override fun onSuccess(t: Response<MenuSectionsResponse>) {

                    menuSectionsLiveData.value = t.body()

                    Log.d("sec", "" + menuSectionsLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return menuSectionsLiveData
    }

    fun loadMenuSlider(auth:String): LiveData<MenuSliderResponse> {
        progressive?.onStarted()
        menuSliderRepository.show_MenuSlider("Bearer "+auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess {  progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<MenuSliderResponse>> {
                override fun onSuccess(t: Response<MenuSliderResponse>) {

                    menuSliderLiveData.value = t.body()

                    Log.d("sec", "" + menuSliderLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposableSlider.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return menuSliderLiveData
    }
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        disposableSlider.clear()

    }

}