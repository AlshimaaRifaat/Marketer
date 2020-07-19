package com.example.themarketer.ui.ProductDetails

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Paint
import android.net.ConnectivityManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themarketer.R
import com.example.themarketer.ui.ProductDetails.Color.ProductColorAdapter
import com.example.themarketer.ui.ProductDetails.Reviews.ProductReviewsAdapter
import com.example.themarketer.ui.ProductDetails.Reviews.ProductReviewsViewModel
import com.example.themarketer.ui.ProductDetails.Reviews.ProductReviewsViewModelFactory
import com.example.themarketer.ui.ProductDetails.Size.ProductSizeAdapter
import com.example.themarketer.utils.Progressive
import com.example.themarketer.utils.loadImage
import com.example.themarketer.utils.toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_product_details.*
import kotlinx.android.synthetic.main.fragment_product_details.view.*


/**
 * A simple [Fragment] subclass.
 */
class ProductDetailsFragment : Fragment() ,View.OnClickListener,Progressive{
    lateinit var navController: NavController
    lateinit var popUpview:View
    lateinit var root:View


    private val productDetailsViewModelFactory=ProducDetailsViewModelFactory()
    private lateinit var productDetailsViewModel: ProductDetailsViewModel
    var productId: String? = null
    private lateinit var sharedPreferences: SharedPreferences
    var userToken: String? = String()
    lateinit var productSizeAdapter: ProductSizeAdapter

    private val productReviewsViewModelFactory=ProductReviewsViewModelFactory()
    private lateinit var productReviewsViewModel: ProductReviewsViewModel

    val productReviewsAdapter = ProductReviewsAdapter()
    lateinit var productColorAdapter: ProductColorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        getUserToken()
        productDetailsViewModel = ViewModelProvider(this,productDetailsViewModelFactory)
            .get(ProductDetailsViewModel::class.java)
        productReviewsViewModel = ViewModelProvider(this,productReviewsViewModelFactory)
            .get(ProductReviewsViewModel::class.java)
        productDetailsViewModel.progressive = this
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_product_details, container, false)


        productId = arguments?.getString("productId")
       // println("productId == " + productId)

        return root
    }

    private fun getUserToken() {
        userToken = sharedPreferences.getString("token", null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.btnAddToCard).setOnClickListener(this)
        view.tSeeAllReviews.setOnClickListener(this)




        rvProductReviews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rvProductReviews.adapter = productReviewsAdapter
        initProductDetailsViewModel()
        initProductReviewsViewModel()

    }

    private fun initProductReviewsViewModel() {
        productReviewsViewModel.loadProductReviews(productId.toString(),userToken.toString())
            .observe(viewLifecycleOwner, Observer {

                productReviewsAdapter.submitList(it.data)

        })

    }


    val isConnected:Boolean
        get(){
            return (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }
    private fun initProductDetailsViewModel() {
        if (isConnected) {


            productDetailsViewModel.loadProducDetails(productId.toString(),userToken.toString())
                .observe(viewLifecycleOwner, Observer {
                    progressProductDetails.visibility = View.GONE

                    if (it != null)
                    {
                        imgProduct.loadImage(it.data.image)
                        tDiscount.text=it.data.discount.toString()+"%"
                        tProductName.text = it.data.name
                        tProductNewPrice.text=it.data.price.get(0).priceAfterDiccount.toString()+" QAR"
                        tProductOldPrice.text=it.data.price.get(0).price.toString()+" QAR"
                        tProductOldPrice.setPaintFlags(tProductOldPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
                        tDescription.text=it.data.description
                        tDiscount.text=it.data.discount.toString()
                        tCamera.text=it.data.camera
                        tScreen.text=it.data.screen
                        tMemory.text=it.data.memory
                        tBattery.text=it.data.battery
                        tSoldBy.text=it.data.store

                        productSizeAdapter = ProductSizeAdapter(it.data.size,requireContext())
                        recyclerProductSize.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                        recyclerProductSize.adapter = productSizeAdapter

                        productColorAdapter = ProductColorAdapter(it.data.color,requireContext())
                        recyclerProductColor.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                        recyclerProductColor.adapter = productColorAdapter


                    }
                })

        } else {

            context?.toast(getResources().getString(R.string.No_network_availabe))
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnAddToCard -> navController!!.navigate(R.id.action_productDetailsFragment_to_myShoppingCartFragment)
            R.id.tSeeAllReviews -> navController!!.navigate(R.id.action_productDetailsFragment_to_reviewsFragment)

        }
    }

  /*  private fun goToLayoutAddRate() {
        val builder = android.app.AlertDialog.Builder(context).create()
        popUpview = LayoutInflater.from(context).inflate(R.layout.layout_add_rate, null)
        builder.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        builder.setView(popUpview)
        // dialog.setCancelable(false);
        builder.show()
    }*/

    override fun onStarted() {
        progressProductDetails.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressProductDetails.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        progressProductDetails.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }
}
