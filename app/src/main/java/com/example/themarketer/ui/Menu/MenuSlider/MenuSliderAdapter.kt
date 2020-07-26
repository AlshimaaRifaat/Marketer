package com.example.themarketer.ui.Menu.MenuSlider

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.themarketer.R
import com.example.themarketer.data.model.Menu.MenuSlider.MenuSliderData
import com.example.themarketer.data.model.Reviews.ReviewsData
import com.example.themarketer.ui.Reviews.ReviewsAdapter
import kotlinx.android.synthetic.main.row_reviews.view.*

class MenuSliderAdapter (private val context: Context, private val imageModelArrayList: ArrayList<MenuSliderData>) : PagerAdapter() {
    private val inflater: LayoutInflater


    init {
        inflater = LayoutInflater.from(context)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return imageModelArrayList.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.row_menu_slider, view, false)!!

        val imgSlider = imageLayout
            .findViewById(R.id.imgSlider) as ImageView


        Glide.with(context).load(imageModelArrayList.get(position).image).into(imgSlider)
        view.addView(imageLayout, 0)

        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }

}
