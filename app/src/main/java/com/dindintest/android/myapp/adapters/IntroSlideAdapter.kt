package com.dindintest.android.myapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.dindintest.android.myapp.R

class IntroSlideAdapter(
    private val introSlides : List<Int>
) : RecyclerView.Adapter<IntroSlideAdapter.IntroSlideViewHolder>() {

    inner class IntroSlideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val imgholder = itemView.findViewById<ImageView>(R.id.ImaimageViewMain)

        fun bind(pos:Int){
            imgholder.setImageResource(introSlides[pos])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return IntroSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_image_slider,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return introSlides.size
    }


}