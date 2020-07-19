package com.example.themarketer.ui.TopMarketersDetails.Products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themarketer.R
import com.example.themarketer.ui.MainActivity
import com.example.themarketer.ui.TopMarketersDetails.FullDayOffer.FullDayOfferAdapter
import kotlinx.android.synthetic.main.fragment_products.*

/**
 * A simple [Fragment] subclass.
 */
class ProductsFragment : Fragment() {
    lateinit var root: View
    private var activity: MainActivity? = null
    val productsAdapter = FullDayOfferAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = activity as MainActivity?
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       root= inflater.inflate(R.layout.fragment_products, container, false)
        //root.btnMainDishesProduct.setOnClickListener { view -> replaceFragment(ProductDetailsFragment()) }
        return root
    }

   /* private fun replaceFragment(fragment: Fragment) {
        requireFragmentManager().beginTransaction().replace(R.id.productsContainer, fragment)
            .addToBackStack(null).commit()

    }*/
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
       rvProducts.layoutManager = LinearLayoutManager(context)
       rvProducts.adapter = productsAdapter

   }

}