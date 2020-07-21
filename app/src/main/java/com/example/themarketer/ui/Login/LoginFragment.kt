package com.example.themarketer.ui.Login

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themarketer.R
import com.example.themarketer.ui.Interests.InterestsFragment
import com.example.themarketer.ui.Register.CreateAccountFragment
import com.example.themarketer.ui.Register.GenderSpinnerAdapter
import com.example.themarketer.utils.Progressive
import com.example.themarketer.utils.toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*

import kotlinx.android.synthetic.main.fragment_login.view.*


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment(), Progressive,View.OnClickListener {
    lateinit var root: View

    private val loginViewModelFactory = LoginViewModelFactory()
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences
    var DeviceToken: String? = String()

    var spinnerPerfixPhoneValue = listOf<String>(
        "+974"
    )
    var perfixPhone_SelectedItemSpinner: String? = null
    lateinit var popUpview:View

        var strFullName: String?=null

    private lateinit var sharedPrefFullName: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(
            this,
            loginViewModelFactory
        ).get(LoginViewModel::class.java)
        loginViewModel.progressive = this
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_login, container, false)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        sharedPrefFullName=PreferenceManager.getDefaultSharedPreferences(activity);
        getUserToken()
       // getFullName()
        getPerfixPhoneCodeSpinnerItem()
        root.btnContinueLogin.setOnClickListener { view ->
            //printErrorMessage()
            initLoginViewModel()
        }




        return root
    }

    private fun getFullName() {
        strFullName = sharedPreferences.getString("fullName", null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.btnResetPassCode?.setOnClickListener(this)
        view?.btnCreateANewAccount?.setOnClickListener(this)


    }
    private fun getPerfixPhoneCodeSpinnerItem() {
        val perfixPhoneCodeSpinnerAdapter =
            GenderSpinnerAdapter(requireContext(), R.layout.spinner_perfix_phone_code_item)
        perfixPhoneCodeSpinnerAdapter.addAll(spinnerPerfixPhoneValue)
        perfixPhoneCodeSpinnerAdapter.add("+974")
        root.spinnerPerfixPhoneCode!!.adapter = perfixPhoneCodeSpinnerAdapter
        root.spinnerPerfixPhoneCode!!.prompt = "+974"
        root.spinnerPerfixPhoneCode!!.setSelection(perfixPhoneCodeSpinnerAdapter.getCount())
        root.spinnerPerfixPhoneCode!!.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    if (root.spinnerPerfixPhoneCode!!.selectedItem == "+974") {
                        perfixPhone_SelectedItemSpinner =
                            root.spinnerPerfixPhoneCode!!.selectedItem.toString()
                    } else {

                        perfixPhone_SelectedItemSpinner =
                            root.spinnerPerfixPhoneCode!!.selectedItem.toString()

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }


    private fun getUserToken() {

        DeviceToken = sharedPreferences.getString("token", null)
    }

    val isConnected: Boolean
        get() {
            return (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }

    private fun replaceFragment(fragment: Fragment) {
        requireFragmentManager().beginTransaction().replace(R.id.loginContainer, fragment).commit()
    }
    private fun replaceWithBackFragment(fragment: Fragment) {
        requireFragmentManager().beginTransaction().replace(R.id.loginContainer, fragment).addToBackStack(null).commit()
    }

    private fun initLoginViewModel() {
        val mobileNumber = etMobileNumber.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        if (mobileNumber.isEmpty()) {
            etMobileNumber.error = "Phone required"
            etMobileNumber.requestFocus()
        } else if (confirmPassword.isEmpty()) {
            etConfirmPassword.error = "Password required"
            etConfirmPassword.requestFocus()
        } else if (etConfirmPassword.length() < 5 || etConfirmPassword.length() > 16) {
            etConfirmPassword.error =
                "Min password must be at least 6 Chrachter and  max 16 Charachter"
            etConfirmPassword.requestFocus()
        } else if (!mobileNumber.isEmpty() && !confirmPassword.isEmpty() && etConfirmPassword.length() >= 5 || etConfirmPassword.length() <= 16) {

            if (isConnected) {

                loginViewModel.loadLogin(mobileNumber, confirmPassword, "0")
                    .observe(viewLifecycleOwner, Observer {
                        progressLogin.visibility = View.GONE
                        context?.toast(it.message)

                        if (it.status == 1) {
                            context?.toast(it.message)
                            val customer_id = it.data.token
                            val strFirstName = it.data.firstName
                            val strLastName = it.data.lastName
                            strFullName = strFirstName + " " + strLastName

                            sharedPreferences.edit().putString("token", customer_id).apply()
                            sharedPrefFullName.edit().putString("fullName", strFullName).apply()
                            replaceFragment(InterestsFragment())
                        } /*else {
                            printErrorMessage()
                        }*/
                    })
            }
            else {
                context?.toast(getResources().getString(R.string.No_network_availabe))
            }

        }

    }

   /* fun printErrorMessage()
    {
        loginViewModel.errorLiveData.observe(viewLifecycleOwner, Observer
        {
            context?.toast(it)
        })
    }*/

    override fun onStarted() {
        progressLogin.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressLogin.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        progressLogin.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnResetPassCode -> { replaceWithBackFragment(CreateAccountFragment()) }
            R.id.btnCreateANewAccount-> {replaceWithBackFragment(CreateAccountFragment())}
        }


    }


}
