package com.example.themarketer.ui.AddCategories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themarketer.R
import com.example.themarketer.data.model.Interests.InterestsData
import com.example.themarketer.ui.Interests.InterestsAdapter
import com.example.themarketer.ui.Interests.InterestsViewModel
import com.example.themarketer.ui.WishList.WishListAdapter
import kotlinx.android.synthetic.main.activity_add_categories.*
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_interests.*
import kotlinx.android.synthetic.main.fragment_interests.view.*

class AddCategoriesActivity : AppCompatActivity() {
    val addCategoriesAdapter = RemoveInterestsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_categories)



        rvAddCategories.layoutManager = GridLayoutManager(this, 3)
        rvAddCategories.adapter = addCategoriesAdapter

    }


}
