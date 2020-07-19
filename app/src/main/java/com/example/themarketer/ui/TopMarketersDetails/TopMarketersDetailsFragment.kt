package com.example.themarketer.ui.TopMarketersDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.themarketer.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_top_marketers_details.*
import kotlinx.android.synthetic.main.fragment_top_marketers_details.view.*

/**
 * A simple [Fragment] subclass.
 */
class TopMarketersDetailsFragment : Fragment() ,View.OnClickListener{
    lateinit var navController: NavController
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var root:View
    lateinit var mBottomSheetDialogView:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_top_marketers_details, container, false)

        root.icLocation.setOnClickListener { view-> goToLayoutLocation() }
        root.icMobileNumber.setOnClickListener { view-> goToLayoutPhone() }

        return root
    }

    private fun goToLayoutPhone() {
        val bottomSheetDialog= BottomSheetDialog(requireContext())
        val view=layoutInflater.inflate(R.layout.layout_phone,null)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    private fun goToLayoutLocation() {
        val bottomSheetDialog= BottomSheetDialog(requireContext())
        val view=layoutInflater.inflate(R.layout.layout_location,null)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
       // view.findViewById<TextView>(R.id.tProducts).setOnClickListener(this)
        viewPagerAdapter =
            ViewPagerAdapter(this)
        pager.adapter = viewPagerAdapter
        TabLayoutMediator(tab, pager) { tab, position ->
            when (position) {
                0 -> tab.text = getResources().getString(R.string.Full_Day_Offer)
                1 -> tab.text = getResources().getString(R.string.Products)
            }

        }.attach()
    }

    override fun onClick(v: View?) {
       when(v!!.id) {
           //  R.id.tProducts -> navController!!.navigate(R.id.action_topMarketersDetailsFragment_to_productDetailsFragment)

       }

       // }
    }


}
