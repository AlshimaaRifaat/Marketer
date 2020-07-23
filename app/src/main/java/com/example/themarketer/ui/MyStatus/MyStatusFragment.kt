package com.example.themarketer.ui.MyStatus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager

import com.example.themarketer.R
import com.example.themarketer.ui.WishList.WishListAdapter
import kotlinx.android.synthetic.main.fragment_my_status.*
import kotlinx.android.synthetic.main.fragment_wish_list.*

/**
 * A simple [Fragment] subclass.
 */
class MyStatusFragment : Fragment() {
    lateinit var navController: NavController
    val myStatusAdapter = MyStatusAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // navController = Navigation.findNavController(view)

        rvMyStatus.layoutManager = GridLayoutManager(context, 5)
        rvMyStatus.adapter = myStatusAdapter
    }
}
