package com.example.themarketer.ui.PopularBrandsDetails

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
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_popular_brands_details.*
import kotlinx.android.synthetic.main.fragment_popular_brands_details.view.*
import kotlinx.android.synthetic.main.fragment_top_marketers_details.view.*
import kotlinx.android.synthetic.main.fragment_wish_list.*

/**
 * A simple [Fragment] subclass.
 */
class PopularBrandsDetailsFragment : Fragment() {
    lateinit var root:View
    val parentProductAdapter = ParentProductAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_popular_brands_details, container, false)
        root.tMainDishes.setOnClickListener { view-> goToLayoutRedeemOffer() }


        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvParentProduct.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        rvParentProduct.adapter = parentProductAdapter
    }
    private fun goToLayoutRedeemOffer() {
        val bottomSheetDialog= BottomSheetDialog(requireContext())
        val view=layoutInflater.inflate(R.layout.layout_redeem_offer,null)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}
