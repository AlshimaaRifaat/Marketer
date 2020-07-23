package com.example.themarketer.ui.TopMarketers

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
import com.example.themarketer.utils.Progressive
import com.example.themarketer.utils.toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_top_marketers.*

/**
 * A simple [Fragment] subclass.
 */
class TopMarketersFragment : Fragment(),Progressive {
    private val topMarketersViewModelFactory = TopMarketersViewModelFactory()
    private lateinit var topMarketersViewModel: TopMarketersViewModel
    lateinit var userToken: String
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var navController: NavController



     val topMarketersAdapter: TopMarketersAdapter=TopMarketersAdapter()

    var sectionId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        getUserToken()
        sectionId = arguments?.getString("sectionId")
        topMarketersViewModel = ViewModelProvider(
            requireActivity(),
            topMarketersViewModelFactory
        ).get(TopMarketersViewModel::class.java)

        topMarketersViewModel.progressive = this
    }

    private fun getUserToken() {
        userToken = sharedPreferences.getString("token", null)!!
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_marketers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


        rvTopMarketers.layoutManager = GridLayoutManager(context, 2)
        rvTopMarketers.adapter = topMarketersAdapter
        initTopMarketersViewModel()
    }

    private fun initTopMarketersViewModel() {
        topMarketersViewModel.loadAllTopMarketersSection(sectionId.toString(),userToken).observe(viewLifecycleOwner, Observer {
            topMarketersAdapter.submitList(it.data.items)

        })

    }

    override fun onStarted() {
        progressTopMarketers.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressTopMarketers.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        progressTopMarketers.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }
}
