package com.example.themarketer.ui.ProductDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.ProductDetails.ProductDetailsResponse
import com.example.themarketer.data.repository.ProducDetailsRepository
import com.example.themarketer.utils.Progressive
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ProductDetailsViewModel : ViewModel() {
    var disposable = CompositeDisposable()
    //var disposableSizes = CompositeDisposable()
    var progressive: Progressive? = null

    val producDetailsLiveData: MutableLiveData<ProductDetailsResponse> by lazy {
        MutableLiveData<ProductDetailsResponse>()
    }



    fun loadProducDetails(productId: String,auth:String): LiveData<ProductDetailsResponse> {
        progressive?.onStarted()
       ProducDetailsRepository().get_ProductDetails(productId,"Bearer "+auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess {  progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<ProductDetailsResponse>> {
                override fun onSuccess(t: Response<ProductDetailsResponse>) {

                    producDetailsLiveData.value = t.body()

                    Log.d("detail", "" + producDetailsLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return producDetailsLiveData
    }





    override fun onCleared() {
        super.onCleared()
        disposable.clear()

    }

}