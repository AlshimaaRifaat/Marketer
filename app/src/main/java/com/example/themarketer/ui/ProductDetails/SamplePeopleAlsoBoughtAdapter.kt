package com.example.themarketer.ui.ProductDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R

class SamplePeopleAlsoBoughtAdapter() : RecyclerView.Adapter<SamplePeopleAlsoBoughtAdapter.MainViewHolder>() {
    // lateinit var mItemCLicked: ItemCLickedListener


    /*  class DiffCallback : DiffUtil.ItemCallback<CountryDetails>() {
          override fun areItemsTheSame(oldItem: CountryDetails, newItem: CountryDetails): Boolean {
              return oldItem.id == newItem.id
          }

          override fun areContentsTheSame(oldItem: CountryDetails, newItem: CountryDetails): Boolean {
              return oldItem.id == newItem.id
          }

      }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_sample_people_also_bought, parent, false)
        return MainViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        //  holder.bind(getItem(position))



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