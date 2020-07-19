package com.example.themarketer.ui.EditFavorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.themarketer.R
import com.example.themarketer.ui.MainActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_edit_favorites.*
import kotlinx.android.synthetic.main.fragment_edit_favorites.view.*

import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class EditFavoritesFragment : Fragment() {
    private lateinit var viewPagerEditFavoritesAdapter: ViewPagerEditFavoritesAdapter
    lateinit var root:View
    companion object {
        var appbar:AppBarLayout?=null
        var tabLayout: TabLayout?=null
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_edit_favorites, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerEditFavoritesAdapter =
            ViewPagerEditFavoritesAdapter(this)
        viewPager.adapter = viewPagerEditFavoritesAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getResources().getString(R.string.Categories)
                1 -> tab.text = getResources().getString(R.string.Marketers)
            }

        }.attach()


    }

}
