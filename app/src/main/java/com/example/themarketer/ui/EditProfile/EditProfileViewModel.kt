package com.example.themarketer.ui.EditProfile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themarketer.data.model.EditProfile.EditProfileResponse
import com.example.themarketer.data.repository.EditProfileRepository
import com.example.themarketer.utils.Progressive
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class EditProfileViewModel  : ViewModel()  {
    var disposable = CompositeDisposable()
    var progressive: Progressive? = null

    val editProfileLiveData: MutableLiveData<EditProfileResponse> by lazy {
        MutableLiveData<EditProfileResponse>()
    }


    fun loadEditProfile(fName: String,lName:String,username:String,phone: String,gender :String,email:String,address:String,image:String,auth:String):
            LiveData<EditProfileResponse> {
        progressive?.onStarted()
        EditProfileRepository().edit_Profile(fName,lName,username,phone,gender,email,address,image,"Bearer "+auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressive?.onStarted() }
            .doOnSuccess {  progressive?.onSuccess() }
            .subscribe(object : SingleObserver<Response<EditProfileResponse>> {
                override fun onSuccess(t: Response<EditProfileResponse>) {

                    editProfileLiveData.value = t.body()

                    Log.d("edit", "" + editProfileLiveData)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { progressive?.onFailure(it) }
                }
            })
        return editProfileLiveData
    }





    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}