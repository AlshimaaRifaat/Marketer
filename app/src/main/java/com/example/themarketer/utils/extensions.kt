package com.example.themarketer.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

import com.example.themarketer.R
import java.util.*

fun Context.goTo(dest: AppCompatActivity, name: String = "", value: String = "") {
    startActivity(Intent(this, dest::class.java).apply {
        putExtra(name, value)
    })
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).placeholder(getCircularProgressDrawable(this.rootView.context))
        .into(this)
}
fun getCircularProgressDrawable(view: Context): CircularProgressDrawable? {
    val circularProgressDrawable = CircularProgressDrawable(view)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        circularProgressDrawable.setColorSchemeColors(view.getColor(R.color.colorAccent));
    }
    circularProgressDrawable.start()
    return circularProgressDrawable
}
/*
open class LangSupport : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context) { //        String lang_code//
//
        val string = newBase.getSharedPreferences("userData", MODE_PRIVATE)
            .getString("mlang", Locale.getDefault().language)
        val context: Context = changeLang(newBase, Locale(string))
        super.attachBaseContext(context)
    }

    open fun setLocale(activity: Activity, lang: String?) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        activity.resources.updateConfiguration(config, null)
    }



}*/
