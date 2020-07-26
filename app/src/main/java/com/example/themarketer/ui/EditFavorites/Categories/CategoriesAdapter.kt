package com.example.themarketer.ui.EditFavorites.Categories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R
import com.example.themarketer.data.model.FavoriteUserCategories.FavoriteUserCategoriesResponse
import com.example.themarketer.utils.loadImage
import kotlinx.android.synthetic.main.row_categories.view.*

class CategoriesAdapter  (userCategoriesList: ArrayList<FavoriteUserCategoriesResponse.Data.Category>, context: Context): ListAdapter<FavoriteUserCategoriesResponse.Data.Category,
        CategoriesAdapter.MainViewHolder>(
    DiffCallback()
) {
   // lateinit var mItemCLicked: ItemCLickedListener


    var userCategoriesList = ArrayList<FavoriteUserCategoriesResponse.Data.Category>()
    private var context: Context? = null


    init {
        this.userCategoriesList = userCategoriesList
        this.context = context
    }

    class DiffCallback : DiffUtil.ItemCallback<FavoriteUserCategoriesResponse.Data.Category>() {
        override fun areItemsTheSame(oldItem: FavoriteUserCategoriesResponse.Data.Category, newItem: FavoriteUserCategoriesResponse.Data.Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteUserCategoriesResponse.Data.Category, newItem: FavoriteUserCategoriesResponse.Data.Category): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_categories, parent, false)
        return MainViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
        val context = holder.itemView.context

        /*holder.itemView.setOnClickListener {
            mItemCLicked.let {
                mItemCLicked.onItemClicked(getItem(position))

                interestsList.remove(getItem(position))
                notifyDataSetChanged()


            }*/


    }


    /* fun setUpListener(itemCLicked: ItemCLickedListener){
        mItemCLicked = itemCLicked

    }*/

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(categoriesData: FavoriteUserCategoriesResponse.Data.Category) {
            itemView.tUserCategoryName.text = categoriesData.name
            itemView.icUserCategory.loadImage(categoriesData.image)

        }
    }

    /*interface ItemCLickedListener {
        fun onItemClicked(interestsData: InterestsData)
    }*/


}