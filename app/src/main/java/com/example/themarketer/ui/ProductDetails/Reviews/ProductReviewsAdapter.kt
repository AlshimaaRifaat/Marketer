package com.example.themarketer.ui.ProductDetails.Reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R
import com.example.themarketer.data.model.ProductReviews.ProductReviewsData
import kotlinx.android.synthetic.main.row_product_reviews.view.*

class ProductReviewsAdapter: ListAdapter<ProductReviewsData,
        ProductReviewsAdapter.MainViewHolder>(
    DiffCallback()
) {

    class DiffCallback : DiffUtil.ItemCallback<ProductReviewsData>() {
        override fun areItemsTheSame(oldItem: ProductReviewsData, newItem: ProductReviewsData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductReviewsData, newItem: ProductReviewsData): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_product_reviews, parent, false)
        return MainViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
        val context = holder.itemView.context
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(reviewsData: ProductReviewsData) {
            itemView.tReviewerName.text = reviewsData.customerName
            itemView.tReviewerDescription.text=reviewsData.body
            itemView.tStarNumber.text="("+reviewsData.star+":0)"
            itemView.rating_bar_evaluate.rating=reviewsData.star.toFloat()

        }
    }




}