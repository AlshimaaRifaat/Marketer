package com.example.themarketer.ui.SearchResult

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.themarketer.Language
import com.example.themarketer.R
import kotlinx.android.synthetic.main.app_bar_search_result.*


class SearchResultActivity : AppCompatActivity() {
    var toolbar: Toolbar?=null
    var drawerLayout: DrawerLayout?=null
    private var toggle: ActionBarDrawerToggle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        toolbar= findViewById(R.id.toolbar)
        drawerLayout=findViewById(R.id.drawerLayout)

        initFilterDrawerLayout()
        if(Language.isRTL())
        {
            imgGoSearchResult.setImageResource(R.drawable.rectangle_left) // Drawabl)


        }else
        {
            imgGoSearchResult.setImageResource(R.drawable.rectangle_17572)

        }


    }

    private fun initFilterDrawerLayout() {
        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        toggle?.syncState()
        drawerLayout?.addDrawerListener(toggle!!)
        toggle?.setDrawerIndicatorEnabled(false)
        constraintFilter.setOnClickListener(View.OnClickListener {
            if (drawerLayout!!.isDrawerOpen(GravityCompat.END)) {
                drawerLayout!!.closeDrawer(GravityCompat.END)
            } else {
                drawerLayout!!.openDrawer(GravityCompat.END)
            }
        })
    }
}
