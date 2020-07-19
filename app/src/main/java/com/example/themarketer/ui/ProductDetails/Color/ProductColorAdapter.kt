package com.example.themarketer.ui.ProductDetails.Color

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.themarketer.R


class ProductColorAdapter (var colorList: List<String>,context: Context) : RecyclerView.Adapter<ProductColorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductColorAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_color, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductColorAdapter.ViewHolder, position: Int) {
        holder.bindItems(colorList)

    }

    override fun getItemCount(): Int {
        return colorList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(colorList: List<String>) {

            val circleImgColor  = itemView.findViewById(R.id.circleImgColor) as ImageView

            val roundRectShape = RoundRectShape(
                floatArrayOf(200f, 200f, 200f, 200f, 200f, 200f, 200f, 200f),
                null,
                null
            )

            val shapeDrawable = ShapeDrawable(roundRectShape)
            if (colorList.get(adapterPosition) != null) shapeDrawable.paint.color =
                Color.parseColor(colorList.get(adapterPosition))
            circleImgColor.setBackground(shapeDrawable)

            if(colorList.get(adapterPosition)=="#fff"||colorList.get(adapterPosition)=="#ffffff"||
                colorList.get(adapterPosition)=="#FFF"||colorList.get(adapterPosition)=="#FFFFFF")
            {
                Color.parseColor(colorList.get(adapterPosition))
                circleImgColor.setBackgroundDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.rounded_circle_white_color))
            }


        }
    }
}