package com.example.themarketer.ui.ProductDetails.Reviews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.Interests.InterestsResponse
import com.example.themarketer.data.model.ProductReviews.ProductReviewsResponse
import com.example.themarketer.data.repository.InterestsRepository
import com.example.themarketer.data.repository.ProductReviewsRepository
import com.example.themarketer.utils.Progressive
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ProductReviewsViewModel:ViewModel() {
    var disposable = CompositeDisposable()
    var progressive: Progressive? = null



    val productReviewsLiveData: MutableLiveData<ProductReviewsResponse> by lazy {
        MutableLiveData<ProductReviewsResponse>()
    }
    fun loadProductReviews(productId:String,auth:String): LiveData<ProductReviewsResponse> {
        progressive?.onStarted()
        ProductReviewsRepository().get_ProductReviews(productId,"Bearer "+auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess {  progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<ProductReviewsResponse>> {
                override fun onSuccess(t: Response<ProductReviewsResponse>) {

                    productReviewsLiveData.value = t.body()

                    Log.d("rest", "" + productReviewsLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return productReviewsLiveData
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}