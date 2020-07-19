package com.example.themarketer.ui.TopMarketersDetails.FullDayOffer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.themarketer.R
import com.example.themarketer.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_full_day_offer.*
import kotlinx.android.synthetic.main.fragment_products.*

/**
 * A simple [Fragment] subclass.
 */
class FullDayOfferFragment : Fragment() {
    lateinit var root: View
    private var activity: MainActivity? = null
    val fullDayOfferAdapter = FullDayOfferAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_full_day_offer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFullDayOffer.layoutManager = LinearLayoutManager(context)
        rvFullDayOffer.adapter = fullDayOfferAdapter

    }
}
