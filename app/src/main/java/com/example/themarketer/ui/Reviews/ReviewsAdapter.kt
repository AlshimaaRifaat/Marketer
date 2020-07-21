package com.example.themarketer.ui.Reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R
import com.example.themarketer.data.model.ProductReviews.ProductReviewsData
import com.example.themarketer.data.model.Reviews.ReviewsData
import kotlinx.android.synthetic.main.row_reviews.view.*

class ReviewsAdapter : ListAdapter<ReviewsData, ReviewsAdapter.MainViewHolder>(
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
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_reviews, parent, false)
        return MainViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(reviewsData: ReviewsData) {
            itemView.tReviewName.text = reviewsData.customerName
            itemView.tReviewDescription.text=reviewsData.body
            itemView.tRateReviewNumber.text="("+reviewsData.star+".0)"
            itemView.ratingBarReview.rating=reviewsData.star.toFloat()

        }
    }


}