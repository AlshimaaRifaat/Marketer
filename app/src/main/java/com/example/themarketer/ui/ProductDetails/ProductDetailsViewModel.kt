package com.example.themarketer.ui.ProductDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.AddReview.AddReviewResponse
import com.example.themarketer.data.model.ProductDetails.ProductDetailsResponse
import com.example.themarketer.data.repository.ProducDetailsRepository
import com.example.themarketer.utils.Progressive
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ProductDetailsViewModel : ViewModel() {
    var disposable = CompositeDisposable()
    var progressive: Progressive? = null
    private val producDetailsRepository= ProducDetailsRepository()
    private val addReviewRepository= ProducDetailsRepository()
    val producDetailsLiveData: MutableLiveData<ProductDetailsResponse> by lazy {
        MutableLiveData<ProductDetailsResponse>()
    }

    val addReviewLiveData: MutableLiveData<AddReviewResponse> by lazy {
        MutableLiveData<AddReviewResponse>()
    }

    fun loadProducDetails(productId: String,auth:String): LiveData<ProductDetailsResponse> {
        progressive?.onStarted()
       producDetailsRepository.get_ProductDetails(productId,"Bearer "+auth)
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


    fun loadAddReview(product_id: String,customer_name: String,body :String,star:Int,auth: String): LiveData<AddReviewResponse>  {
        progressive?.onStarted()
        addReviewRepository.add_Review(product_id,customer_name,body,star,"Bearer "+auth)
            .enqueue(object : Callback, retrofit2.Callback<AddReviewResponse> {
                override fun onResponse(call: Call<AddReviewResponse>, response: Response<AddReviewResponse>) {
                    progressive?.onSuccess()
                    addReviewLiveData?.setValue(response.body())

                }

                override fun onFailure(call: Call<AddReviewResponse>, t: Throwable) {
                    // loginLiveData?.setValue()

                }
            })
        return addReviewLiveData
    }
    override fun onCleared() {
        super.onCleared()
        disposable.clear()

    }

}