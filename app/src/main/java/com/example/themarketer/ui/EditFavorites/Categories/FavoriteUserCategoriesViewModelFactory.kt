package com.example.themarketer.ui.EditFavorites.Categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themarketer.ui.Interests.InterestsViewModel

class FavoriteUserCategoriesViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteUserCategoriesViewModel() as T
    }
}