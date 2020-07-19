package com.example.themarketer.ui.Register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themarketer.ui.Login.LoginViewModel

class RegisterViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel() as T
    }
}