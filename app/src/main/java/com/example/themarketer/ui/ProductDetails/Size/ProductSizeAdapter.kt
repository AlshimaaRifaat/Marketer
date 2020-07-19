package com.example.themarketer.ui.ProductDetails.Size

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R

class ProductSizeAdapter (var sizeList: List<String>,context: Context) : RecyclerView.Adapter<ProductSizeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSizeAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_product_size, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductSizeAdapter.ViewHolder, position: Int) {
        holder.bindItems(sizeList)

    }

    override fun getItemCount(): Int {
        return sizeList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(sizeList: List<String>) {

            val tSize  = itemView.findViewById(R.id.tSize) as TextView
            tSize.text=sizeList.get(adapterPosition)



        }
    }
}