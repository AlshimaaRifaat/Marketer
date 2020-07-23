package com.example.themarketer.ui.Menu.MenuSlider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R
import com.example.themarketer.data.model.Menu.MenuSlider.MenuSliderData
import com.example.themarketer.data.model.Reviews.ReviewsData
import com.example.themarketer.ui.Reviews.ReviewsAdapter
import kotlinx.android.synthetic.main.row_reviews.view.*

class MenuSliderAdapter : ListAdapter<MenuSliderData, MenuSliderAdapter.MainViewHolder>(
    DiffCallback()
) {


    class DiffCallback : DiffUtil.ItemCallback<MenuSliderData>() {
        override fun areItemsTheSame(oldItem: MenuSliderData, newItem: MenuSliderData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MenuSliderData, newItem: MenuSliderData): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_menu_slider, parent, false)
        return MainViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(menuSliderData: MenuSliderData) {



        }
    }


}