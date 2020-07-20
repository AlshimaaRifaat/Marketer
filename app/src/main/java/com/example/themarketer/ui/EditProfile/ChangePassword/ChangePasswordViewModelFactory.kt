package com.example.themarketer.ui.EditProfile.ChangePassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChangePasswordViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChangePasswordViewModel() as T
    }
}