package com.example.themarketer.ui.PopularBrandsDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.themarketer.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_top_marketers_details.view.*

/**
 * A simple [Fragment] subclass.
 */
class PopularBrandsDetailsFragment : Fragment() {
    lateinit var root:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_popular_brands_details, container, false)
        root.imgLogo.setOnClickListener { view-> goToLayoutRedeemOffer() }


        return root
    }
    private fun goToLayoutRedeemOffer() {
        val bottomSheetDialog= BottomSheetDialog(requireContext())
        val view=layoutInflater.inflate(R.layout.layout_redeem_offer,null)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}
