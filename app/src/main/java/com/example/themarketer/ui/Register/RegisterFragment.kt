package com.example.themarketer.ui.Register

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ImageView
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themarketer.Language
import com.example.themarketer.R
import com.example.themarketer.ui.Interests.InterestsFragment
import com.example.themarketer.utils.Progressive
import com.example.themarketer.utils.toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_interests.view.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_menu.view.*
import kotlinx.android.synthetic.main.fragment_protect_account.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*


/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment(),Progressive {
    lateinit var root: View


    private val registerViewModelFactory = RegisterViewModelFactory()
    private lateinit var registerViewModel: RegisterViewModel
     var spinnerGenderValue = listOf<String>(
        "male",
        "female"
    )



    var gender_SelectedItemSpinner:String? = null



    //var genderSpinner: Spinner? = null
    var DeviceToken:String?= String()
    var name:String?= String()
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        getUserToken()
       // getName()
        registerViewModel = ViewModelProvider(
            requireActivity(),
            registerViewModelFactory
        ).get(RegisterViewModel::class.java)
        registerViewModel.progressive = this
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_register, container, false)

        getGenderSpinnerItems()
        root.btnConfirmRegister.setOnClickListener { view -> initRegisterViewModel()}

        return root
    }

    private fun getUserToken() {

        DeviceToken=sharedPreferences.getString("token",null)

    }
    private fun getName() {

        name=sharedPreferences.getString("name",null)
    }

    val isConnected:Boolean
        get(){
            return (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }
    private fun initRegisterViewModel() {
        val userName = etUserName.text.toString().trim()



        if (userName.isEmpty()) {
            icUserNameValidate.setImageResource(R.drawable.icon_close)
            constraintUserName.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.constraint_wrong_input))
        } else if (gender_SelectedItemSpinner.equals("")) {
          context?.toast(getString(R.string.Gender_required))
        }  else  {
            icUserNameValidate.setImageResource(R.drawable.icon_check)
            if (isConnected) {

                //note:check param
                registerViewModel.loadRegister("f","l",userName,CreateAccountFragment.phone.toString()
                    ,gender_SelectedItemSpinner.toString(),ProtectAccountFragment.password.toString(),
                    ProtectAccountFragment.confirmPassword.toString())
                    .observe(requireActivity(), Observer {
                        progressBarRegister.visibility = View.GONE
                        if (it != null) {
                            context?.toast(it.message)
                            sharedPreferences.edit().putString("token", it.data.token).apply()
                            //sharedPreferences.edit().putString("name",it.data.name)
                           replaceFragment(InterestsFragment())

                        }
                    })

            } else {

                context?.toast(getResources().getString(R.string.No_network_availabe))
            }
        }
    }


    private fun getGenderSpinnerItems() {
        val genderSpinnerAdapter =
            GenderSpinnerAdapter(requireContext(), R.layout.spinner_item)
        genderSpinnerAdapter.addAll(spinnerGenderValue)
        genderSpinnerAdapter.add("Gender")
        root.genderSpinner!!.adapter = genderSpinnerAdapter
        root.genderSpinner!!.prompt = "Gender"
        root.genderSpinner!!.setSelection(genderSpinnerAdapter.getCount())
        root.genderSpinner!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (root.genderSpinner!!.selectedItem == "Gender") {
                } else {
                    gender_SelectedItemSpinner = root.genderSpinner!!.selectedItem.toString()


                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        requireFragmentManager().beginTransaction().replace(R.id.register_container, fragment)
            .commit()

    }

    override fun onStarted() {
        progressBarRegister.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressBarRegister.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        progressBarRegister.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }
}
