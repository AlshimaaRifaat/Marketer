package com.example.themarketer.ui.Menu.MenuSections

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
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
import com.example.themarketer.data.model.Menu.MenuSections.SectionsData
import com.example.themarketer.data.model.Menu.MenuSlider.MenuSliderData
import com.example.themarketer.ui.Menu.MenuSlider.MenuSliderAdapter
import com.example.themarketer.ui.SearchResult.SearchResultActivity
import com.example.themarketer.utils.Progressive
import com.example.themarketer.utils.goTo
import com.example.themarketer.utils.toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_menu.view.*
import java.util.*


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
   /* private lateinit var childList: MutableList<SectionItem>*/

    private val menuSliderViewModelFactory = MenuSectionsViewModelFactory()
    private lateinit var menuSliderViewModel: MenuSectionsViewModel
   private var mContext: Context? = null

    lateinit var root:View
    var swipeTimer: Timer?=null
    val handler = Handler()
    val Update = Runnable {
        if (currentPage == NUM_PAGES) {
            currentPage = 0
        }
        root.viewPagerSlider!!.setCurrentItem(currentPage++, true)
    }
    private var CartNumber = 0
    private var currentPage = 0
    private var NUM_PAGES = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        getUserToken()
        parentList = mutableListOf<SectionsData>()
        menuSectionsViewModel = ViewModelProvider(
            requireActivity(),
            menuSectionsViewModelFactory
        ).get(MenuSectionsViewModel::class.java)

        menuSliderViewModel = ViewModelProvider(
            requireActivity(),
            menuSliderViewModelFactory
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
        root= inflater.inflate(R.layout.fragment_menu, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


        view.imgMenuGoSearch.setOnClickListener(this)


        if (Language.isRTL()) {
            view.imgMenuGoSearch.setImageResource(R.drawable.rectangle_menu_left)


        } else {
            view.imgMenuGoSearch.setImageResource(R.drawable.rectangle17572)

        }
        initMenuSectionsViewModel()
        initMenuSliderViewModel()
    }

    private fun initMenuSliderViewModel() {
        menuSectionsViewModel.loadMenuSlider(userToken).observe(viewLifecycleOwner, Observer {
            if(it!=null) {
                viewPagerSlider!!.adapter = this.context?.let { it1 ->
                    MenuSliderAdapter(
                        it1,
                        it.data as ArrayList<MenuSliderData>
                    )
                }

                root.viewPagerCircleIndicator.setViewPager(viewPagerSlider)
                NUM_PAGES = it.data.size
                swipeTimer = Timer()
                swipeTimer!!.schedule(object : TimerTask() {
                    override fun run() {
                        handler.post(Update)
                    }
                }, 3000, 3000)
            }
        })
    }

    private fun initMenuSectionsViewModel() {
        menuSectionsViewModel.loadMenuSections(userToken).observe(viewLifecycleOwner, Observer {
        if(it!=null) {
            parentList.addAll(it.data)

           menuParentSectionsAdapter = MenuParentSectionsAdapter(parentList, requireContext())
           view?.rvParentSections?.layoutManager =
               LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
           view?.rvParentSections?.adapter = menuParentSectionsAdapter
        }
        })
    }

 /*   fun onAttach(context: Context) {
        super.onAttach(this)
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }*/
    override fun onClick(v: View?) {
        when(v!!.id){
           R.id.imgMenuGoSearch -> {
               context?.goTo(SearchResultActivity())
           }



        }
    }
    override fun onStarted() {
        view?.progressMenu?.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        view?.progressMenu?.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        view?.progressMenu?.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

}
