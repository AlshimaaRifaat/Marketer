package com.example.themarketer.ui.Menu.MenuSections

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themarketer.Language
import com.example.themarketer.R
import com.example.themarketer.data.model.MenuSections.SectionsData
import com.example.themarketer.utils.Progressive
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_menu.view.*

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() ,View.OnClickListener, Progressive {

    private val menuSectionsViewModelFactory = MenuSectionsViewModelFactory()
    private lateinit var menuSectionsViewModel: MenuSectionsViewModel
    lateinit var userToken: String
    private lateinit var sharedPreferences: SharedPreferences

    lateinit var navController: NavController


    lateinit var menuParentSectionsAdapter: MenuParentSectionsAdapter

    private lateinit var parentList: MutableList<SectionsData>
   // private lateinit var childList: MutableList<SectionItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        getUserToken()
        parentList = mutableListOf<SectionsData>()
        menuSectionsViewModel = ViewModelProvider(
            requireActivity(),
            menuSectionsViewModelFactory
        ).get(MenuSectionsViewModel::class.java)

        menuSectionsViewModel.progressive = this
    }

    private fun getUserToken() {
        userToken = sharedPreferences.getString("token", null)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


        view.imgMenuGoSearch.setOnClickListener(this)
        initMenuSectionsViewModel()

        if (Language.isRTL()) {
            view.imgMenuGoSearch.setImageResource(R.drawable.rectangle_menu_left)


        } else {
            view.imgMenuGoSearch.setImageResource(R.drawable.rectangle17572)

        }

    }

    private fun initMenuSectionsViewModel() {
        menuSectionsViewModel.loadMenuSections(userToken).observe(requireActivity(), Observer {

            parentList.addAll(it.data)
            menuParentSectionsAdapter = MenuParentSectionsAdapter(parentList, requireContext())
            rvParentSections.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            rvParentSections.adapter = menuParentSectionsAdapter
        })


    }


    override fun onClick(v: View?) {
        when(v!!.id){
           R.id.imgMenuGoSearch -> navController!!.navigate(R.id.action_menuFragment_to_searchResultFragment)



        }
    }
    override fun onStarted() {
        progressMenu.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressMenu.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        progressMenu.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

}
