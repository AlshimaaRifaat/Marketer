package com.example.themarketer.ui.Reviews

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
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
import com.example.themarketer.data.model.Interests.InterestsData
import com.example.themarketer.ui.Interests.InterestsAdapter
import com.example.themarketer.ui.Interests.InterestsViewModel
import com.example.themarketer.utils.Progressive
import com.example.themarketer.utils.toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_interests.*
import kotlinx.android.synthetic.main.fragment_reviews.*

/**
 * A simple [Fragment] subclass.
 */
class ReviewsFragment : Fragment() ,Progressive{
    private val reviewsViewModelFactory = ReviewsViewModelFactory()
    private lateinit var reviewsViewModel: ReviewsViewModel
    lateinit var userToken: String
    private lateinit var sharedPreferences: SharedPreferences
    val reviewsAdapter = ReviewsAdapter()

    var productId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productId = arguments?.getString("product_id")
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        getUserToken()
        reviewsViewModel = ViewModelProvider(
            requireActivity(),
            reviewsViewModelFactory
        ).get(ReviewsViewModel::class.java)

        reviewsViewModel.progressive = this
    }

    private fun getUserToken() {
        userToken = sharedPreferences.getString("token", null)!!


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reviews, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvReviews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        rvReviews.adapter = reviewsAdapter
       initReviewsViewModel()



    }
    val isConnected: Boolean
        get() {
            return (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }
    private fun initReviewsViewModel() {
        if (isConnected) {
            context?.toast(productId.toString())
            reviewsViewModel.loadReviews(productId.toString(), userToken).observe(requireActivity(), Observer {
                reviewsAdapter.submitList(it.data)

            })
        }else {
            context?.toast(getResources().getString(R.string.No_network_availabe))
        }

    }

    override fun onStarted() {
        progressReviews.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressReviews.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        progressReviews.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }
}
