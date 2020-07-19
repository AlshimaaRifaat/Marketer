package com.example.themarketer.ui.ChooseLoginRegister

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.themarketer.R
import com.example.themarketer.ui.Login.LoginFragment
import com.example.themarketer.ui.Register.CreateAccountFragment
import com.example.themarketer.ui.Register.RegisterFragment
import kotlinx.android.synthetic.main.fragment_choose_login_register.view.*

/**
 * A simple [Fragment] subclass.
 */
class ChooseLoginRegisterFragment : Fragment(){
    lateinit var root:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_choose_login_register, container, false)
        root.btnLogin.setOnClickListener { view -> replaceFragment(LoginFragment()) }
        root.btnCreateNewAccount.setOnClickListener { view -> replaceFragment(CreateAccountFragment()) }
        return root
    }
    private fun replaceFragment(fragment: Fragment) {
        requireFragmentManager().beginTransaction().replace(R.id.chooseLoginRegisterContainer, fragment)
            .addToBackStack(null).commit()

    }




}
