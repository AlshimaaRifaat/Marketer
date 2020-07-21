package com.example.themarketer.ui.Profile

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.themarketer.Language
import com.example.themarketer.ui.ChooseLoginRegister.ChooseLoginRegisterFragment
import com.example.themarketer.ui.Login.LoginFragment
import com.example.themarketer.ui.Profile.LogOut.LogOutViewModel
import com.example.themarketer.ui.Profile.LogOut.LogOutViewModelFactory
import com.example.themarketer.utils.Progressive
import com.example.themarketer.utils.toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import android.util.Base64;
import android.util.Log
import com.example.themarketer.R
import com.example.themarketer.ui.GettingStarted.GettingStartedActivity
import com.example.themarketer.utils.goTo
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.io.File
import java.io.FileOutputStream
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(),View.OnClickListener,Progressive {
    lateinit var navController: NavController

    lateinit var root: View

    private val authProfileViewModelFactory = AuthProfileViewModelFactory()
    private lateinit var authProfileViewModel: AuthProfileViewModel

    lateinit var UserToken: String
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    companion object {
        var userFirstName: String?=null
        var userLastName:String?=null
        var userEmail: String?=null
        var userPhone:String?=null
        var userAddress:String?=null
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
        private val IMAGE_DIRECTORY = "/demonuts"

        var file: File? = File("")
        var encImage:String?=null
        //lateinit var path:String
    }
    var userName: String?=null

    private val logOutViewModelFactory = LogOutViewModelFactory()
    private lateinit var logOutViewModel: LogOutViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        getUserToken()
        authProfileViewModel = ViewModelProvider(
            requireActivity(),
            authProfileViewModelFactory
        ).get(AuthProfileViewModel::class.java)
        authProfileViewModel.progressive = this

        logOutViewModel = ViewModelProvider(
            requireActivity(),
            logOutViewModelFactory
        ).get(LogOutViewModel::class.java)



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initAuthProfileViewModel()

        // handleLogOutCase()
        view.btnLogOut.setOnClickListener(this)
        view.btnEdit.setOnClickListener(this)
        view.constraintMyStatus.setOnClickListener(this)
        view.constraintMyProfile.setOnClickListener(this)
        view.constraintMyWishlist.setOnClickListener(this)
        view.constraintFavorites.setOnClickListener(this)
        if(Language.isRTL())
        {
            view.icArrowMyStatus.setImageResource(R.drawable.nexxt) // Drawabl)
            view.icArrowMyProfile.setImageResource(R.drawable.nexxt)
            view.icArrowFavorites.setImageResource(R.drawable.nexxt)
            view.icArrowWishlist.setImageResource(R.drawable.nexxt)

        }else
        {
            view.icArrowMyStatus.setImageResource(R.drawable.next3)
            view.icArrowMyProfile.setImageResource(R.drawable.next3)
            view.icArrowFavorites.setImageResource(R.drawable.next3)
            view.icArrowWishlist.setImageResource(R.drawable.next3)

        }
        view.icCameraPick.setOnClickListener(this)

    }

 /*   private fun handleLogOutCase() {

        if(UserToken==null)
        {
            constraint_Login.visibility=View.VISIBLE
            btnLogOut.visibility=View.GONE

        }
        constraint_Login.setOnClickListener { replaceFragment(LoginFragment()) }
    }*/


    private fun getUserToken() {

        UserToken = sharedPreferences.getString("token", null)!!

    }

    private fun initAuthProfileViewModel() {

        authProfileViewModel.loadAuthProfile(UserToken)
            .observe(requireActivity(), Observer {
                progressProfile.visibility = View.GONE
                if (it != null) {
                    userFirstName=it.data.firstName
                    userLastName=it.data.lastName
                    userName=userFirstName+" "+userLastName
                    userEmail=it.data.email
                    userPhone=it.data.phone
                    userAddress=it.data.address

                    view?.tName?.text=userName
                    //note:check i need arabian food
                   // view?.tDescription?.text=userEmail
                    Glide.with(this).load(it.data.image)
                        .error(R.drawable.empty_img_profile).into(imgProfile)

                }
            })

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnEdit -> navController!!.navigate(R.id.action_profileFragment_to_editProfileFragment)
            R.id.constraintMyStatus -> navController!!.navigate(R.id.action_profileFragment_to_myStatusFragment)
            R.id.constraintMyProfile-> navController!!.navigate(R.id.action_profileFragment_to_editProfileFragment)
            R.id.constraintFavorites -> navController!!.navigate(R.id.action_profileFragment_to_editFavoritesFragment)
            R.id.constraintMyWishlist -> navController!!.navigate(R.id.action_profileFragment_to_wishlistFragment)
            R.id.btnLogOut-> {
                initLogOutViewModel()
                context?.goTo(GettingStartedActivity())}
            R.id.icCameraPick-> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (context?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED){
                        //permission denied
                        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                        //show popup to request runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();
                    }
                }
                else{
                    //system OS is < Marshmallow
                    pickImageFromGallery();
                }
            }
        }
    }
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }
    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    context?.toast("Permission denied")
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE&& data != null && data.data != null){

            if (data != null)
            {
                val contentURI = data!!.data
                val filePath = getRealPathFromURIPath(contentURI!!, requireActivity())
                file = File(filePath)

                Glide.with(requireActivity()).load("file:" + file).into(imgProfile);

                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, contentURI)
                    imgProfile?.setImageBitmap(bitmap)
                    val bytes = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, bytes)
                    val dat: ByteArray = bytes.toByteArray()
                    encImage = Base64.encodeToString(dat, Base64.DEFAULT)
                    Log.d("encoded", "" + encImage.toString())
                    context?.toast(encImage.toString())



                }
                catch (e: IOException) {
                    e.printStackTrace()

                }

            }
        }
    }

    private fun getRealPathFromURIPath(contentURI: Uri, activity: Activity): String {
        val cursor = activity.contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) {
            return contentURI.getPath()!!
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor.getString(idx)
        }
    }





    private fun initLogOutViewModel() {


        logOutViewModel.loadLogOut(UserToken)
            .observe(requireActivity(), Observer {
                progressProfile.visibility = View.GONE
                if (it != null) {
                    context?.toast(it.message)
/*
                    editor = sharedPreferences.edit()
                    editor.remove("token")
                    editor.clear()
                    editor.apply()
*/
                    sharedPreferences.edit().putString("token", null).apply()

                }
            })

    }

    override fun onStarted() {
        progressProfile.visibility = View.VISIBLE

    }

    override fun onSuccess() {
        progressProfile.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        progressProfile.visibility = View.GONE
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

}

