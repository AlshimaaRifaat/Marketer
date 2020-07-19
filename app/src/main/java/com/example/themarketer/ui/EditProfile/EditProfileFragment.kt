package com.example.themarketer.ui.EditProfile

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.themarketer.R
import com.example.themarketer.ui.Interests.InterestsFragment
import com.example.themarketer.ui.Profile.ProfileFragment
import com.example.themarketer.ui.Register.CreateAccountFragment
import com.example.themarketer.ui.Register.ProtectAccountFragment
import com.example.themarketer.ui.Register.RegisterViewModel
import com.example.themarketer.ui.Register.RegisterViewModelFactory
import com.example.themarketer.utils.Progressive
import com.example.themarketer.utils.toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class EditProfileFragment : Fragment(),Progressive,View.OnClickListener{
    lateinit var navController: NavController
    private val editProfileViewModelFactory = EditProfileViewModelFactory()
    private lateinit var editProfileViewModel: EditProfileViewModel

    lateinit var userToken: String
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var popUpview:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        getUserToken()
        editProfileViewModel = ViewModelProvider(
            requireActivity(),
            editProfileViewModelFactory
        ).get(EditProfileViewModel::class.java)
        editProfileViewModel.progressive = this
      //  context?.toast(ProfileFragment.file.toString())
        //var filee:String=ProfileFragment.file.toString()
      //  Log.d("file", "" + ProfileFragment.file.toString())
    }

    private fun getUserToken() {
        userToken=sharedPreferences.getString("token", null)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProfileData()
        view.btnSave.setOnClickListener(this)
        view.tChangePassword.setOnClickListener(this)


    }

    private fun setProfileData() {
        view?.etFirst_Name?.setText(ProfileFragment.userFirstName.toString())
        view?.etLast_Name?.setText(ProfileFragment.userLastName.toString())
            view?.et_Email?.setText(ProfileFragment.userEmail.toString())
        view?.etMobile_Number?.setText(ProfileFragment.userPhone.toString())
        view?.etAddress?.setText(ProfileFragment.userAddress.toString())

    }

    override fun onStarted() {
        progressEditProfile.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressEditProfile.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        progressEditProfile.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnSave-> {initEditProfileViewModel()}
            R.id.tChangePassword-> {goToLayoutChangePassword()}
        }
    }

    private fun goToLayoutChangePassword() {
        val builder = android.app.AlertDialog.Builder(context).create()
        popUpview = LayoutInflater.from(context).inflate(R.layout.layout_change_password, null)
        builder.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        builder.setView(popUpview)

        builder.show()
    }

    val isConnected:Boolean
        get(){
            return (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }
    private fun initEditProfileViewModel() {
        val first_Name = etFirst_Name.text.toString()
        val lastName=etLast_Name.text.toString()
        val e_mail = et_Email.text.toString()
        val mobile_number = etMobile_Number.text.toString()
        val address=etAddress.text.toString()
       context?.toast(userToken)
        if (isConnected) {
//note: check static param later
            editProfileViewModel.loadEditProfile(first_Name,lastName ,"user",mobile_number,"female",e_mail,address,
                ProfileFragment.encImage.toString(),userToken)
                .observe(requireActivity(), Observer {
                    progressEditProfile.visibility = View.GONE
                    if (it != null) {
                        context?.toast(it.message)

                    }
                })

        } else {

            context?.toast(getResources().getString(R.string.No_network_availabe))
        }
    }

}