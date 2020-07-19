package com.example.themarketer.ui.Menu.MenuSections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themarketer.ui.Interests.InterestsViewModel

class MenuSectionsViewModelFactory   : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MenuSectionsViewModel() as T
    }
}