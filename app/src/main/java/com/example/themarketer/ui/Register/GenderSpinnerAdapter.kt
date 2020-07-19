package com.example.themarketer.ui.Register

import android.content.Context
import android.widget.ArrayAdapter




class GenderSpinnerAdapter(context: Context, resource: Int) :
    ArrayAdapter<String?>(context, resource) {
    override fun getCount(): Int {
        val count = super.getCount()
        return if (count > 0) count - 1 else count
    }
}