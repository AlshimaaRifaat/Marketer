package com.example.themarketer.ui.TopMarketers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themarketer.ui.Interests.InterestsViewModel

class TopMarketersViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopMarketersViewModel() as T
    }
}