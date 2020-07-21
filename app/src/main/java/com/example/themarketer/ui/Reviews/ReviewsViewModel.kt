package com.example.themarketer.ui.Reviews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.AddInterests.AddInterestsResponse
import com.example.themarketer.data.model.DeleteUserInterests.DeleteUserInterestsResponse
import com.example.themarketer.data.model.Interests.InterestsResponse
import com.example.themarketer.data.model.Reviews.ReviewsResponse
import com.example.themarketer.data.repository.AddInterestsRepository
import com.example.themarketer.data.repository.DeleteUserInterestsRepository
import com.example.themarketer.data.repository.InterestsRepository
import com.example.themarketer.data.repository.ReviewsRepository
import com.example.themarketer.utils.Progressive
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ReviewsViewModel  : ViewModel() {
    var disposable = CompositeDisposable()
    var progressive: Progressive? = null

    val reviewsLiveData: MutableLiveData<ReviewsResponse> by lazy {
        MutableLiveData<ReviewsResponse>()
    }

    fun loadReviews(product_id:String,auth:String): LiveData<ReviewsResponse> {
        progressive?.onStarted()
        ReviewsRepository().show_Reviews(product_id,"Bearer "+auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess {  progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<ReviewsResponse>> {
                override fun onSuccess(t: Response<ReviewsResponse>) {

                    reviewsLiveData.value = t.body()

                    Log.d("rest", "" + reviewsLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return reviewsLiveData
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()

    }

}