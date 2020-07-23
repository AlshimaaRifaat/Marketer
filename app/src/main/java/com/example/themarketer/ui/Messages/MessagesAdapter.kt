package com.example.themarketer.ui.Messages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R

class MessagesAdapter (): RecyclerView.Adapter<MessagesAdapter.MainViewHolder>() {
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_messages, parent, false)
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


            view.findNavController().navigate(R.id.action_messageFragment_to_chatFragment)



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