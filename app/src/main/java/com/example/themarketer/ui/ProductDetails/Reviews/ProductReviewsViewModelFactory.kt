package com.example.themarketer.ui.ProductDetails.Reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themarketer.ui.Interests.InterestsViewModel

class ProductReviewsViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductReviewsViewModel() as T
    }
}