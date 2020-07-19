package com.example.themarketer.ui.Interests

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themarketer.R
import com.example.themarketer.SplashActivity
import com.example.themarketer.data.model.Interests.InterestsData
import com.example.themarketer.ui.GettingStarted.GettingStartedActivity
import com.example.themarketer.ui.MainActivity
import com.example.themarketer.utils.Progressive
import com.example.themarketer.utils.goTo
import com.example.themarketer.utils.toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_interests.*
import kotlinx.android.synthetic.main.fragment_interests.view.*


/**
 * A simple [Fragment] subclass.
 */
class InterestsFragment : Fragment() , Progressive,View.OnClickListener {
    lateinit var root: View

    private val interestsViewModelFactory = InterestsViewModelFactory()
    private lateinit var interestsViewModel: InterestsViewModel
    lateinit var userToken: String
    private lateinit var sharedPreferences: SharedPreferences

    var selectedCategoryIdList = ArrayList<Int>()

    lateinit var name: String

    lateinit var interestsDataList: ArrayList<InterestsData>
    lateinit var adapter: InterestsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        getUserToken()
       // getName()
        interestsDataList=  ArrayList<InterestsData>()
        adapter = InterestsAdapter(interestsDataList,context = requireContext())
        interestsViewModel = ViewModelProvider(
            requireActivity(),
            interestsViewModelFactory
        ).get(InterestsViewModel::class.java)

        interestsViewModel.progressive = this
    }

    private fun getUserToken() {
        userToken = sharedPreferences.getString("token", null)!!


    }
   /* private fun getName() {
        name = sharedPreferences.getString("name", null)!!
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_interests, container, false)
       // root.tName.setOnClickListener { view ->   activity?.goTo(MainActivity())  }
        return root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* if(!name.equals(null)) {
            tWelcomeName.setText(name).toString()
        }*/
        rvInterests.layoutManager = GridLayoutManager(context, 3)
        rvInterests.adapter = adapter
        initInterestsViewModel()

        view.btnContinue.setOnClickListener(this)
        view.tSkip.setOnClickListener(this)

    }

    private fun initInterestsViewModel() {

            interestsViewModel.loadInterests(userToken).observe(requireActivity(), Observer {

                adapter.submitList(it.data)

            })



        adapter.setUpListener(object : InterestsAdapter.ItemCLickedListener {
            override fun onItemClicked(interestsData: InterestsData) {

             /*  if(selectedCategoryIdList.contains(interestsData.id)) {
                   selectedCategoryIdList.remove(interestsData.id)
               }else{*/
                   selectedCategoryIdList.add(interestsData.id)
              // }

                context?.toast(interestsData.id.toString())
                interestsViewModel.loadDeleteUserInterests(interestsData.id.toString(),userToken).observe(requireActivity(), Observer {
                    context?.toast(it.message)
                    initInterestsViewModel()
                })


            }

        })
      /*  adapter.setUpDeleteListener(object : InterestsAdapter.ItemDeleteCLickedListener{
            override fun onItemDeleteClicked(interestsData: InterestsData, position: Int) {
                interestsViewModel.loadDeleteUserInterests(interestsData.id.toString(),userToken).observe(requireActivity(), Observer {
                    context?.toast(it.message)
                    initInterestsViewModel()
                })
            }
        })*/




    }

    override fun onStarted() {
        progressInterests.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressInterests.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        progressInterests.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnContinue -> {initAddInterestsViewModel() }
            R.id.tSkip -> {  context?.goTo(MainActivity()) }
        }
    }


    private fun initAddInterestsViewModel() {
        interestsViewModel.loadAddInterests(selectedCategoryIdList,userToken).observe(requireActivity(), Observer {
        context?.toast(it.message)
           context?.goTo(MainActivity())

        })
    }


}
