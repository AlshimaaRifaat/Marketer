package com.example.themarketer.ui.EditFavorites.Categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R
import com.example.themarketer.ui.WishList.WishListAdapter

class CategoriesAdapter  (): RecyclerView.Adapter<CategoriesAdapter.MainViewHolder>() {
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_categories, parent, false)
        return MainViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

       /* holder.itemView.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_wishlistFragment_to_productDetailsFragment)
        }*/


    }

    override fun getItemCount(): Int {
        return 20
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }



}