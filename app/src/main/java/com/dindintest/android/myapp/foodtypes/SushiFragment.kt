package com.dindintest.android.myapp.foodtypes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.airbnb.mvrx.*
import com.dindintest.android.myapp.FoodViewModel
import com.dindintest.android.myapp.R
import com.dindintest.android.myapp.adapters.FoodAdapter
import kotlinx.android.synthetic.main.fragment_sushi.*

class SushiFragment : BaseMvRxFragment() {
    private lateinit var foodAdapter: FoodAdapter

    private val foodViewModel: FoodViewModel by activityViewModel()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sushi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodAdapter = FoodAdapter(object : FoodAdapter.CartlistListener {
            override fun addToCart(dishId: Long) {
                // call ViewModel to add movie to watchlist
                foodViewModel.addSushi(dishId)
            }

            override fun removeFromCart(dishId: Long) {
                // call ViewModel to remove movie from watchlist
                foodViewModel.removeSushi(dishId)
            }
        })
        sushi_recyclerview.adapter = foodAdapter
        foodViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }


    override fun invalidate() {
        withState(foodViewModel) { state ->

            when (state.sushis) {
                is Loading -> {
                    progress_bar_sushi.visibility = View.VISIBLE
                    sushi_recyclerview.visibility = View.GONE
                }
                is Success -> {
                    progress_bar_sushi.visibility = View.GONE
                    sushi_recyclerview.visibility = View.VISIBLE
                    foodAdapter.setDishes(state.sushis.invoke())
                }
                is Fail -> {
                    Toast.makeText(requireContext(), "Failed to load all dishes", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}