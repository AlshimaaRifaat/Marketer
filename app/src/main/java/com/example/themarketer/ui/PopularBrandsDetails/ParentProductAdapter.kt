package com.example.themarketer.ui.PopularBrandsDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R
import com.example.themarketer.data.model.Menu.MenuSections.SectionItem
import com.example.themarketer.data.model.Menu.MenuSections.SectionsData
import com.example.themarketer.ui.Menu.MenuSections.MenuChildSectionsAdapter
import com.example.themarketer.ui.Menu.MenuSections.MenuParentSectionsAdapter
import kotlinx.android.synthetic.main.row_menu_parent_sections.view.*
import kotlinx.android.synthetic.main.row_parent_product.view.*

class ParentProductAdapter () : RecyclerView.Adapter<ParentProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParentProductAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.row_child_product, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ParentProductAdapter.ViewHolder, position: Int) {


    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return 20
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}