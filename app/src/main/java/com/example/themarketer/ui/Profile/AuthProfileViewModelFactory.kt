package com.example.themarketer.ui.Profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themarketer.ui.Login.LoginViewModel

class AuthProfileViewModelFactory  : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthProfileViewModel() as T
    }
}