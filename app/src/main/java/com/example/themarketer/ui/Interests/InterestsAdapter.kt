package com.example.themarketer.ui.Interests

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R
import com.example.themarketer.data.model.Interests.InterestsData
import com.example.themarketer.utils.loadImage
import kotlinx.android.synthetic.main.row_interests.view.*

class InterestsAdapter (interestsList: ArrayList<InterestsData>, context: Context): ListAdapter<InterestsData,
        InterestsAdapter.MainViewHolder>(
    DiffCallback()
) {
    lateinit var mItemCLicked: ItemCLickedListener

   // lateinit var mItemDeleteCLicked: ItemDeleteCLickedListener
    var interestsList = ArrayList<InterestsData>()
    private var context: Context? = null



    init {
        this.interestsList = interestsList
        this.context = context
    }
    class DiffCallback : DiffUtil.ItemCallback<InterestsData>() {
        override fun areItemsTheSame(oldItem: InterestsData, newItem: InterestsData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: InterestsData, newItem: InterestsData): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_interests, parent, false)
        return MainViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
        val context = holder.itemView.context
        holder.itemView.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.product_item_corner))
       holder.itemView.setOnClickListener {
          //  mItemCLicked.let {
                //mItemCLicked.onItemClicked(getItem(position))
             holder.itemView.constraintItemInterest.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.group4833))

                   // mItemDeleteCLicked.onItemDeleteClicked(interestsList!!.get(position),position)
                    interestsList.remove(getItem(position))
           notifyItemRemoved(position);
           notifyItemRangeChanged(position, interestsList.size);

           // }

         /*  holder.itemView.setOnClickListener {
               mItemDeleteCLicked.let {
               mItemDeleteCLicked.onItemDeleteClicked(interestsList!!.get(position),position)
               interestsList?.removeAt(position)
               notifyDataSetChanged()

           }
           }*/

        }


    }
    fun setUpListener(itemCLicked: ItemCLickedListener){
        mItemCLicked = itemCLicked

    }
    /*fun setUpDeleteListener(itemDeleteCLicked: ItemDeleteCLickedListener){
        mItemDeleteCLicked = itemDeleteCLicked
    }*/
    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(interestsData: InterestsData) {
            itemView.tInterestName.text = interestsData.name
            itemView.imgInterest.loadImage(interestsData.image)

        }
    }

      interface ItemCLickedListener {
        fun onItemClicked(interestsData: InterestsData)
    }
   /* interface ItemDeleteCLickedListener {
        fun onItemDeleteClicked(interestsData: InterestsData,position:Int)
    }*/

}