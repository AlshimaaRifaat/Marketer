package com.example.themarketer.ui.ProductDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProducDetailsViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductDetailsViewModel() as T
    }
}