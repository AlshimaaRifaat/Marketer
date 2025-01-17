package com.example.themarketer.ui.EditFavorites.Marketers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.themarketer.R
import com.example.themarketer.ui.EditFavorites.Categories.CategoriesAdapter
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_marketers.*

/**
 * A simple [Fragment] subclass.
 */
class MarketersFragment : Fragment() {
    val marketersAdapter = MarketersAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marketers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMarketers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        rvMarketers.adapter = marketersAdapter
    }
}
