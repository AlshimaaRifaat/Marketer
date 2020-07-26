package com.example.themarketer.ui.EditFavorites.Categories

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.themarketer.R
import com.example.themarketer.data.model.FavoriteUserCategories.FavoriteUserCategoriesResponse
import com.example.themarketer.ui.AddCategories.AddCategoriesActivity
import com.example.themarketer.utils.Progressive
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_categories.view.*

/**
 * A simple [Fragment] subclass.
 */
class CategoriesFragment : Fragment(),Progressive {
    lateinit var root: View
    private val favoriteUserCategoriesViewModelFactory = FavoriteUserCategoriesViewModelFactory()
    private lateinit var favoriteUserCategoriesViewModel: FavoriteUserCategoriesViewModel
    lateinit var userToken: String
    private lateinit var sharedPreferences: SharedPreferences



    lateinit var userCategoriesDataList: ArrayList<FavoriteUserCategoriesResponse.Data.Category>
    lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        getUserToken()
        // getName()
        userCategoriesDataList=  ArrayList<FavoriteUserCategoriesResponse.Data.Category>()
        categoriesAdapter = CategoriesAdapter(userCategoriesDataList,context = requireContext())
        favoriteUserCategoriesViewModel = ViewModelProvider(
            requireActivity(),
            favoriteUserCategoriesViewModelFactory
        ).get(FavoriteUserCategoriesViewModel::class.java)

        favoriteUserCategoriesViewModel.progressive = this
    }


    private fun getUserToken() {
        userToken = sharedPreferences.getString("token", null)!!
    }
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


        view?.rvCategories?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        view?.rvCategories?.adapter = categoriesAdapter
        initFavoriteUserCategoriesViewModel()
    }

    private fun initFavoriteUserCategoriesViewModel() {
        favoriteUserCategoriesViewModel.loadFavoriteUserCategories(userToken).observe(requireActivity(), Observer {

            categoriesAdapter.submitList(it.data.categories)

        })
    }


    override fun onStarted() {
        view?.progressUserCategories?.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        view?.progressUserCategories?.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        view?.progressUserCategories?.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }


}
