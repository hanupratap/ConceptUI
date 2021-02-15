package com.dindintest.android.myapp.adapters

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dindintest.android.myapp.R
import com.dindintest.android.myapp.data.FoodModel

class OrderListAdapter(private val cartListener: OrderListAdapter.CartlistListener) :
    RecyclerView.Adapter<OrderListAdapter.MfoodViewHolder>()  {

    private val dishes = mutableListOf<FoodModel>()

    fun setDishes(foods: List<FoodModel>) {
        Log.d(ContentValues.TAG, "INORDER: $foods")
        this.dishes.clear()
        this.dishes.addAll(foods)
        notifyDataSetChanged()
    }

    inner class MfoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dish_img: ImageView = itemView.findViewById(R.id.cart_img)
        val dish_name: TextView = itemView.findViewById(R.id.dish_name)
        val dish_price: TextView = itemView.findViewById(R.id.dish_price)
        val dish_close: ImageButton = itemView.findViewById(R.id.dish_close)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MfoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dish_item_cart, parent, false)

        return MfoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: MfoodViewHolder, position: Int) {
        val food = dishes[position]

        Glide
            .with(holder.itemView)
            .load(food.pic)
            .centerCrop()
            .into(holder.dish_img)

        holder.dish_name.text = food.name

        holder.dish_price.text = "${food.price} usd"


        holder.dish_close.setOnClickListener {
            cartListener.removeFromCart(food.id)
        }
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    interface CartlistListener {
        fun removeFromCart(dishId: Long)
    }

}