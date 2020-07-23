package com.example.themarketer.ui.EditFavorites.Categories

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.themarketer.R
import com.example.themarketer.ui.AddCategories.AddCategoriesActivity
import com.example.themarketer.ui.WishList.WishListAdapter
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_categories.view.*
import kotlinx.android.synthetic.main.fragment_wish_list.*

/**
 * A simple [Fragment] subclass.
 */
class CategoriesFragment : Fragment() {
    lateinit var root: View
    val categoriesAdapter = CategoriesAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_categories, container, false)

        root.btnAddCategories.setOnClickListener { view -> goToAddCategoriesActivity() }
        return root
    }

    private fun goToAddCategoriesActivity() {
        val intent = Intent(activity, AddCategoriesActivity::class.java)
        startActivity(intent)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvCategories.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        rvCategories.adapter = categoriesAdapter
    }


}
