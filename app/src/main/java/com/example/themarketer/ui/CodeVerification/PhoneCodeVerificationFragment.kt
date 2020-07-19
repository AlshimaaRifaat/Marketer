package com.example.themarketer.ui.CodeVerification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.themarketer.R
import com.example.themarketer.ui.Register.ProtectAccountFragment
import kotlinx.android.synthetic.main.fragment_phone_code_verification.view.*

/**
 * A simple [Fragment] subclass.
 */
class PhoneCodeVerificationFragment : Fragment() {
    lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_phone_code_verification, container, false)
        root.btnValidate.setOnClickListener { view -> replaceFragment(ProtectAccountFragment()) }

        return root
    }

    private fun replaceFragment(fragment: Fragment) {
        requireFragmentManager().beginTransaction().replace(R.id.phone_code_verification_Container, fragment)
            .addToBackStack(null).commit()

    }

}
