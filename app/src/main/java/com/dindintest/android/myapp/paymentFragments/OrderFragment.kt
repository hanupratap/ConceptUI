package com.dindintest.android.myapp.paymentFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.airbnb.mvrx.*
import com.dindintest.android.myapp.FoodViewModel
import com.dindintest.android.myapp.R
import com.dindintest.android.myapp.adapters.OrderListAdapter
import kotlinx.android.synthetic.main.fragment_order.*


class OrderFragment : BaseMvRxFragment() {

    private lateinit var orderAdapter: OrderListAdapter
    var value:Int = 0

    private val foodViewModel: FoodViewModel by fragmentViewModel()

    override fun invalidate() {
        //
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderAdapter = OrderListAdapter(object : OrderListAdapter.CartlistListener {
            override fun removeFromCart(dishId: Long) {
                // call ViewModel to remove movie from watchlist
//                foodViewModel.removePizza(dishId)
//                foodViewModel.removeDrinks(dishId)
//                foodViewModel.removeSushi(dishId)
            }
        })

        val list = foodViewModel.getItemsInCart()
        orderAdapter.setDishes(list)
        for(item in list){
            value += item.price
        }

        value_usd.text = "$value usd"

        cart_items.adapter = orderAdapter
        foodViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }


    private fun showError() {
        Toast.makeText(requireContext(), "Failed to load dishes", Toast.LENGTH_SHORT).show()
    }

}