package com.example.themarketer.ui.Register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.themarketer.R
import com.example.themarketer.utils.toast
import kotlinx.android.synthetic.main.fragment_protect_account.*
import kotlinx.android.synthetic.main.fragment_protect_account.etConfirmPassword
import kotlinx.android.synthetic.main.fragment_protect_account.view.*


/**
 * A simple [Fragment] subclass.
 */
class ProtectAccountFragment : Fragment() {
    lateinit var root: View


    companion object {
        var password: String?=null
        var confirmPassword: String?=null
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_protect_account, container, false)

        root.btnContinueRegister.setOnClickListener { view -> replaceFragment(RegisterFragment()) }
        return root
    }

    private fun replaceFragment(fragment: Fragment) {
       password =etPassword.text.toString().trim()
        confirmPassword =etConfirmPassword.text.toString().trim()
        if (password!!.isEmpty()) {

            icPasswordValidate.setImageResource(R.drawable.icon_close)
            constraintPassword.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.constraint_wrong_input))
        }else if(confirmPassword!!.isEmpty()) {

            icConfirmPasswordValidate.setImageResource(R.drawable.icon_close)
            constraintConfirmPassword.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.constraint_wrong_input))
        } else if(password!!.isEmpty()&&confirmPassword!!.isEmpty()) {

            icPasswordValidate.setImageResource(R.drawable.icon_close)
            constraintPassword.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.constraint_wrong_input))
            icConfirmPasswordValidate.setImageResource(R.drawable.icon_close)
            constraintConfirmPassword.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.constraint_wrong_input))
        }
        else if (password != confirmPassword) {
            context?.toast(getString(R.string.password_and_confirmation))
            icConfirmPasswordValidate.setImageResource(R.drawable.icon_close)
            constraintConfirmPassword.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.constraint_wrong_input))
        }else if (etPassword.length() < 6 || etPassword.length() > 16) {
            icPasswordValidate.setImageResource(R.drawable.icon_close)
            constraintPassword.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.constraint_wrong_input))
            icConfirmPasswordValidate.setImageResource(R.drawable.icon_close)
            constraintConfirmPassword.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.constraint_wrong_input))
            context?.toast(getString(R.string.Min_password_must_be_at_least_6_Chrachter))
        }
            else {
            icPasswordValidate.setImageResource(R.drawable.icon_check)
            icConfirmPasswordValidate.setImageResource(R.drawable.icon_check)
            btnContinueRegister.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.btn_continue))
            requireFragmentManager().beginTransaction()
                .replace(R.id.protectAccountContainer, fragment)
                .addToBackStack(null).commit()
        }

    }
}
