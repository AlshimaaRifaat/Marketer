package com.example.themarketer.ui.ProductDetails.Reviews

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
import com.example.themarketer.data.model.ProductReviews.ReviewsData
import com.example.themarketer.ui.Interests.InterestsAdapter
import com.example.themarketer.utils.loadImage
import kotlinx.android.synthetic.main.row_interests.view.*
import kotlinx.android.synthetic.main.row_product_reviews.view.*

class ProductReviewsAdapter: ListAdapter<ReviewsData,
        ProductReviewsAdapter.MainViewHolder>(
    DiffCallback()
) {

    class DiffCallback : DiffUtil.ItemCallback<ReviewsData>() {
        override fun areItemsTheSame(oldItem: ReviewsData, newItem: ReviewsData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReviewsData, newItem: ReviewsData): Boolean {
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

        fun bind(reviewsData: ReviewsData) {
            itemView.tReviewerName.text = reviewsData.customerName
            itemView.tReviewerDescription.text=reviewsData.body
            itemView.tStarNumber.text="("+reviewsData.star+":0)"
            itemView.rating_bar_evaluate.rating=reviewsData.star.toFloat()

        }
    }




}