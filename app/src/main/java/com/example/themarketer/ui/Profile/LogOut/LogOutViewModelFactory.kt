package com.example.themarketer.ui.Profile.LogOut

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themarketer.ui.Profile.AuthProfileViewModel

class LogOutViewModelFactory  : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LogOutViewModel() as T
    }
}