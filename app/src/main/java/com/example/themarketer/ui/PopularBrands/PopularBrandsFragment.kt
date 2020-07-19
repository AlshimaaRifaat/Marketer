package com.example.themarketer.ui.PopularBrands

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager

import com.example.themarketer.R
import com.example.themarketer.ui.TopMarketers.TopMarketersAdapter
import com.example.themarketer.ui.TopMarketers.TopMarketersViewModel
import com.example.themarketer.ui.TopMarketers.TopMarketersViewModelFactory
import com.example.themarketer.utils.Progressive
import com.example.themarketer.utils.toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_popular_brands.*
import kotlinx.android.synthetic.main.fragment_top_marketers.*

/**
 * A simple [Fragment] subclass.
 */
class PopularBrandsFragment : Fragment(),Progressive {
    private val popularBrandsViewModelFactory = TopMarketersViewModelFactory()
    private lateinit var popularBrandsViewModel: TopMarketersViewModel
    lateinit var userToken: String
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var navController: NavController



    val popularBrandsAdapter: TopMarketersAdapter = TopMarketersAdapter()

    var sectionId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        getUserToken()
        sectionId = arguments?.getString("sectionId")
        popularBrandsViewModel = ViewModelProvider(
            requireActivity(),
            popularBrandsViewModelFactory
        ).get(TopMarketersViewModel::class.java)

        popularBrandsViewModel.progressive = this
    }

    private fun getUserToken() {
        userToken = sharedPreferences.getString("token", null)!!
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_brands, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


        rvPopularBrands.layoutManager = GridLayoutManager(context, 2)
        rvPopularBrands.adapter = popularBrandsAdapter
        initPopularBrandsViewModel()
    }

    private fun initPopularBrandsViewModel() {
        context?.toast(sectionId.toString())
        popularBrandsViewModel.loadAllPopularBrandSection(sectionId.toString(),userToken).observe(viewLifecycleOwner, Observer {
            popularBrandsAdapter.submitList(it.data.items)

        })

    }

    override fun onStarted() {
        progressPopularBrands.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressPopularBrands.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        progressPopularBrands.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }
}
