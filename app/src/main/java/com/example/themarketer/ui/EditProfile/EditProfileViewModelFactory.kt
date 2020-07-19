package com.example.themarketer.ui.EditProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themarketer.ui.Register.RegisterViewModel

class EditProfileViewModelFactory  : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditProfileViewModel() as T
    }
}