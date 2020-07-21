package com.example.themarketer.ui.Reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themarketer.ui.Interests.InterestsViewModel

class ReviewsViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReviewsViewModel() as T
    }
}