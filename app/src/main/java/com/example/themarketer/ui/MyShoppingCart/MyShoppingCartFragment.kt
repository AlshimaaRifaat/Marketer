package com.example.themarketer.ui.MyShoppingCart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themarketer.R
import com.example.themarketer.ui.MainActivity
import com.example.themarketer.ui.Reviews.ReviewsAdapter
import kotlinx.android.synthetic.main.fragment_my_shopping_cart.*
import kotlinx.android.synthetic.main.fragment_my_shopping_cart.view.*
import kotlinx.android.synthetic.main.fragment_product_details.view.*
import kotlinx.android.synthetic.main.fragment_reviews.*


/**
 * A simple [Fragment] subclass.
 */
class MyShoppingCartFragment : Fragment(),View.OnClickListener {
    lateinit var root: View
    lateinit var navController: NavController
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
        navController = Navigation.findNavController(view)
        view.btnRedeem.setOnClickListener(this)
        /* */
        rvMyShoppingCart.layoutManager = LinearLayoutManager(context)
        rvMyShoppingCart.adapter = myShoppingCartAdapter




    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnRedeem -> navController!!.navigate(R.id.action_myShoppingCartFragment_to_redeemOfferStatusFragment)
        }
    }
}
