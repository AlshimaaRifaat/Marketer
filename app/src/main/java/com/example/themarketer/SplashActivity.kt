package com.example.themarketer

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import com.example.themarketer.ui.AddCategories.AddCategoriesActivity
import com.example.themarketer.ui.GettingStarted.GettingStartedActivity
import com.example.themarketer.ui.Interests.InterestsFragment
import com.example.themarketer.utils.goTo
import com.example.themarketer.utils.toast

class SplashActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
companion object{
    var userToken: String?=null
}

    var name: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        userToken = sharedPreferences.getString("token", null);
        // name=sharedPreferences.getString("name",null);
        handleGoTo()

    }

    private fun handleGoTo() {
        if (userToken!=null) {
            replaceFragment(InterestsFragment())

        } else {
            val intent = Intent(this, GettingStartedActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            ?.beginTransaction()
            ?.replace(
                R.id.splashContainer,
                fragment
            )
            ?.commitNow()
    }
}
