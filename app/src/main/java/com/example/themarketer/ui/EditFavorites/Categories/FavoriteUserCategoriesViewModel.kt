package com.example.themarketer.ui.EditFavorites.Categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.FavoriteUserCategories.FavoriteUserCategoriesResponse
import com.example.themarketer.data.repository.FavoriteUserCategoiesRepository
import com.example.themarketer.utils.Progressive
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class FavoriteUserCategoriesViewModel  : ViewModel() {
    var disposable = CompositeDisposable()
    var progressive: Progressive? = null


    //var deleteUserInterestsdisposable = CompositeDisposable()


    val favoriteUserCategoriesLiveData: MutableLiveData<FavoriteUserCategoriesResponse> by lazy {
        MutableLiveData<FavoriteUserCategoriesResponse>()
    }


   /* val DeleteUserInterestsLiveData: MutableLiveData<DeleteUserInterestsResponse> by lazy {
        MutableLiveData<DeleteUserInterestsResponse>()
    }*/
    fun loadFavoriteUserCategories(auth:String): MutableLiveData<FavoriteUserCategoriesResponse> {
        progressive?.onStarted()
        FavoriteUserCategoiesRepository().showFavoriteUserCategoies("Bearer "+auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess {  progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<FavoriteUserCategoriesResponse>> {
                override fun onSuccess(t: Response<FavoriteUserCategoriesResponse>) {

                    favoriteUserCategoriesLiveData.value = t.body()


                    Log.d("fav", "" + favoriteUserCategoriesLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return favoriteUserCategoriesLiveData
    }







    /*fun loadDeleteUserInterests(categoryId: String,auth: String): LiveData<DeleteUserInterestsResponse> {
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
    }*/


    override fun onCleared() {
        super.onCleared()
        disposable.clear()

    }

}