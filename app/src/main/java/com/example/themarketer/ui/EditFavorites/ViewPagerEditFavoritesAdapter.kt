package com.example.themarketer.ui.EditFavorites

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.themarketer.ui.EditFavorites.Categories.CategoriesFragment
import com.example.themarketer.ui.EditFavorites.Marketers.MarketersFragment


class ViewPagerEditFavoritesAdapter ( fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    private val baseId: Int = 0
    override fun createFragment(position: Int): Fragment = when (position) {

        0 -> CategoriesFragment()
        1 -> MarketersFragment()
        else -> throw IllegalStateException("Invalid adapter position")
    }



}