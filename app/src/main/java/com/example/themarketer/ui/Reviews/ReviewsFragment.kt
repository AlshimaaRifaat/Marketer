package com.example.themarketer.ui.Reviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.themarketer.R
import com.example.themarketer.ui.WishList.WishListAdapter
import kotlinx.android.synthetic.main.fragment_reviews.*
import kotlinx.android.synthetic.main.fragment_wish_list.*

/**
 * A simple [Fragment] subclass.
 */
class ReviewsFragment : Fragment() {
    val reviewsAdapter = ReviewsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reviews, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /* */
        rvReviews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        rvReviews.adapter = reviewsAdapter




    }
}
