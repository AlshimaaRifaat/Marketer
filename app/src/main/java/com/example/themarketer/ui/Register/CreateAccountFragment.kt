package com.example.themarketer.ui.Register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.themarketer.R
import com.example.themarketer.ui.CodeVerification.PhoneCodeVerificationFragment
import kotlinx.android.synthetic.main.app_bar_search_result.*
import kotlinx.android.synthetic.main.fragment_create_account.view.*
import kotlinx.android.synthetic.main.fragment_payment.*


/**
 * A simple [Fragment] subclass.
 */
class CreateAccountFragment : Fragment() {
lateinit var root: View
    companion object {
        var phone: String?=null
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_create_account, container, false)

        root.btnSendPassCode.setOnClickListener { v -> replaceFragment(PhoneCodeVerificationFragment()) }
        return root
    }

    private fun replaceFragment(fragment: Fragment) {

        phone=etPhone.text.toString().trim()

        if (phone!!.isEmpty()) {
            etPhone.error = "Phone required"
            etPhone.requestFocus()

        } else if (etPhone!!.length() < 11) {
            etPhone.error = "Phone must be 11 digits"
            etPhone.requestFocus()

        }else {
        /*    val aFragment: Fragment = CreateAccountFragment()

            requireFragmentManager().beginTransaction()
                //.remove( null)
                .add(R.id.create_account_container, aFragment, "fragment-a")
                .commit();

            //val fragment: Fragment = PhoneCodeVerificationFragment()
            requireFragmentManager().findFragmentById(R.id.create_account_container)?.let {
                requireFragmentManager().beginTransaction()
                    .remove(it) // resolves to A_Fragment instance
                    .add(R.id.create_account_container, fragment, "fragment-b")
                    .addToBackStack("a")
                    .commit()
            }*/

          requireFragmentManager().beginTransaction().replace(R.id.create_account_container, fragment)
                .addToBackStack(null).commit()
        }

    }


}
