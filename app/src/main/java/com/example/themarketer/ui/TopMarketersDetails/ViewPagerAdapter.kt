package com.example.themarketer.ui.TopMarketersDetails

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.themarketer.ui.TopMarketersDetails.FullDayOffer.FullDayOfferFragment
import com.example.themarketer.ui.TopMarketersDetails.Products.ProductsFragment

class ViewPagerAdapter  (fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {

        0 -> FullDayOfferFragment()
        1 -> ProductsFragment()
        else -> throw IllegalStateException("Invalid adapter position")
    }


}


