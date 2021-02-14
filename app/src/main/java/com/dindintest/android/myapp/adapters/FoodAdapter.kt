package com.dindintest.android.myapp.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dindintest.android.myapp.data.FoodModel
import com.dindintest.android.myapp.R


class FoodAdapter(private val cartListener: CartlistListener) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

  private val dishes = mutableListOf<FoodModel>()

  fun setDishes(foods: List<FoodModel>) {
    Log.d(TAG, "setDishes: ${foods}")
    this.dishes.clear()
    this.dishes.addAll(foods)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.food_viewholder_layout, parent, false)

    return FoodViewHolder(view)
  }

  override fun getItemCount() = dishes.size

  override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
    val food = dishes[position]

    Glide
        .with(holder.itemView)
        .load(food.desc)
        .centerCrop()
        .into(holder.posterImageView)

    holder.foodNameTextView.text = food.name

    holder.cartButton.text = "${food.price} usd"


    holder.cartButton.setOnClickListener {
      holder.cartButton_super.visibility = View.VISIBLE
      if (food.incart) {

//        cartListener.removeFromCart(food.id)
      } else {
        cartListener.addToCart(food.id)
      }
    }
  }

  inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val posterImageView: ImageView = itemView.findViewById(R.id.dish_poster_imageview)
    val foodNameTextView: TextView = itemView.findViewById(R.id.dish_name_textview)
    val cartButton: Button = itemView.findViewById(R.id.add_cart_button)
    val cartButton_super: Button = itemView.findViewById(R.id.add_cart_button_superpose)
  }

  interface CartlistListener {
    fun addToCart(dishId: Long)

    fun removeFromCart(dishId: Long)
  }
}