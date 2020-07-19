package com.example.themarketer.ui.TopMarketersDetails.FullDayOffer

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R
import com.example.themarketer.ui.MainActivity

class FullDayOfferAdapter (): RecyclerView.Adapter<FullDayOfferAdapter.MainViewHolder>() {
    // lateinit var mItemCLicked: ItemCLickedListener
    var context: MainActivity? = null

    /*  class DiffCallback : DiffUtil.ItemCallback<CountryDetails>() {
          override fun areItemsTheSame(oldItem: CountryDetails, newItem: CountryDetails): Boolean {
              return oldItem.id == newItem.id
          }

          override fun areContentsTheSame(oldItem: CountryDetails, newItem: CountryDetails): Boolean {
              return oldItem.id == newItem.id
          }

      }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_full_day_offer, parent, false)
        return MainViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        //  holder.bind(getItem(position))
        holder.itemView.setOnClickListener {view ->
            /* mItemCLicked.let {
                 mItemCLicked.onItemClicked(getItem(position))
             }*/
           // val context = holder.itemView.context


           // val context = holder.itemView.context



        }


    }
    /* fun setUpInterestsListener(itemCLicked: ItemCLickedListener){
        mItemCLicked = itemCLicked
    }*/
    override fun getItemCount(): Int {
        return 20
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /* fun bind(countryDetails: CountryDetails) {
             itemView.country.text = countryDetails.name

         }*/
    }

    /* interface ItemInterestsCLickedListener {
           fun onInterestsItemClicked(countryDetails: CountryDetails)
       }*/

}