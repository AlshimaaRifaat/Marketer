package com.example.themarketer.ui.MyShoppingCart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themarketer.R
import com.example.themarketer.ui.MainActivity
import com.example.themarketer.ui.Reviews.ReviewsAdapter
import kotlinx.android.synthetic.main.fragment_my_shopping_cart.*
import kotlinx.android.synthetic.main.fragment_reviews.*


/**
 * A simple [Fragment] subclass.
 */
class MyShoppingCartFragment : Fragment() {
    lateinit var root: View
    val myShoppingCartAdapter = MyShoppingCartAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_my_shopping_cart, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /* */
        rvMyShoppingCart.layoutManager = LinearLayoutManager(context)
        rvMyShoppingCart.adapter = myShoppingCartAdapter




    }
}
