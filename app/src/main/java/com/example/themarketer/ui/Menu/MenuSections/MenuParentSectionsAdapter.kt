package com.example.themarketer.ui.Menu.MenuSections

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R
import com.example.themarketer.data.model.MenuSections.SectionItem
import com.example.themarketer.data.model.MenuSections.SectionsData
import kotlinx.android.synthetic.main.row_menu_parent_sections.view.*

class MenuParentSectionsAdapter(var parentList: List<SectionsData>, private var context: Context):
    RecyclerView.Adapter<MenuParentSectionsAdapter.MainViewHolder>() {
    // lateinit var mItemCLicked: ItemCLickedListener



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_menu_parent_sections, parent, false)
        return MainViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindItems(parentList[position])
        val childObject: List<SectionItem> =parentList!!.get(position).items
          var designType:String=parentList!!.get(position).designType

        holder.itemView.rvMenuChildSections.setAdapter(MenuChildSectionsAdapter(childObject,designType,
            context = context!!.applicationContext))
        if(designType=="1")
        {
            holder.itemView.tMore.visibility=View.VISIBLE
            holder.itemView.tMore.setOnClickListener { view ->
                val bundle = bundleOf(
                    "sectionId" to parentList!!.get(position).id.toString())
                view.findNavController().navigate(R.id.action_menuFragment_to_topMarketerFragment,bundle)  }
        }else if(designType=="2")
        {
            holder.itemView.tMore.visibility=View.GONE
        }else
        {
            holder.itemView.tMore.visibility=View.VISIBLE
           holder.itemView.tMore.setOnClickListener { view ->
               val bundle = bundleOf(
                   "sectionId" to parentList!!.get(position).id.toString())
                view.findNavController().navigate(R.id.action_menuFragment_to_popularBrandsFragment,bundle)  }
        }


    }

    override fun getItemCount(): Int {

        return parentList!!.size
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(sectionsData: SectionsData) {
            val tSectionName  = itemView.findViewById(R.id.tSectionName) as TextView
            var rvMenuChildSections  = itemView.findViewById(R.id.rvMenuChildSections) as RecyclerView
            val tMore  = itemView.findViewById(R.id.tMore) as TextView
            tSectionName.text =sectionsData.name


        }
    }



}