package com.example.themarketer.ui.PopularBrands

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R
import com.example.themarketer.data.model.AllSection.AllSectionItem
import com.example.themarketer.ui.TopMarketers.TopMarketersAdapter
import com.example.themarketer.utils.loadImage
import kotlinx.android.synthetic.main.row_popular_brands.view.*
import kotlinx.android.synthetic.main.row_top_marketers.view.*

class PopularBrandsAdapter  : ListAdapter<AllSectionItem,
        PopularBrandsAdapter.MainViewHolder>(
    DiffCallback()
) {
    //lateinit var mItemCLicked: ItemCLickedListener

    // lateinit var mItemDeleteCLicked: ItemDeleteCLickedListener

    class DiffCallback : DiffUtil.ItemCallback<AllSectionItem>() {
        override fun areItemsTheSame(oldItem: AllSectionItem, newItem: AllSectionItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AllSectionItem, newItem: AllSectionItem): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_popular_brands, parent, false)
        return MainViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))

        /* holder.itemView.setOnClickListener {
             mItemCLicked.let {
                 mItemCLicked.onItemClicked(getItem(position))
                 holder.itemView.constraintItemInterest.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.group4833))


                 interestsList.remove(getItem(position))
                 notifyDataSetChanged()


             }



         }*/


    }
    /* fun setUpListener(itemCLicked: ItemCLickedListener){
         mItemCLicked = itemCLicked

     }*/

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(allSectionItem: AllSectionItem) {
            itemView.tItemBrandName.text = allSectionItem.name
            itemView.imgBrandItem.loadImage(allSectionItem.image)
            itemView.tBrandPadge.text=allSectionItem.padge

        }
    }

    /*  interface ItemCLickedListener {
          fun onItemClicked(interestsData: InterestsData)
      }*/


}