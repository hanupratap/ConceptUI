package com.dindintest.android.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.airbnb.mvrx.*
import com.dindintest.android.myapp.adapters.FoodAdapter
import com.dindintest.android.myapp.data.FoodModel
import kotlinx.android.synthetic.main.fragment_dishlist.*


class FoodFragment : BaseMvRxFragment() {

  private lateinit var foodAdapter: FoodAdapter

  // add ViewModel declaration here
  private val foodViewModel: FoodViewModel by fragmentViewModel()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_dishlist, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Toast.makeText(requireContext(), "This is Food Fragment", Toast.LENGTH_SHORT).show()

    foodAdapter = FoodAdapter(object : FoodAdapter.CartlistListener {
      override fun addToCart(dishId: Long) {
        // call ViewModel to add movie to watchlist
        foodViewModel.addPizza(dishId)
      }

      override fun removeFromCart(dishId: Long) {
        // call ViewModel to remove movie from watchlist
        foodViewModel.removePizza(dishId)
      }
    })
    dishes_recyclerview.adapter = foodAdapter
    foodViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
      Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    })
  }

  override fun invalidate() {
    // modify UI
    withState(foodViewModel) { state ->
      when (state.pizzas) {
        is Loading -> {
          showLoader()
        }
        is Success -> {
          val foodAddedtoCart = state.pizzas.invoke().filter { it.incart>0 }
          showDishListed(foodAddedtoCart)
        }
        is Fail -> {
          showError()
        }
      }
    }
  }

  private fun showLoader() {
    progress_bar_pizza.visibility = View.VISIBLE
    empty_watchlist_textview.visibility = View.GONE
    dishes_recyclerview.visibility = View.GONE
  }

  private fun showDishListed(addedFoods: List<FoodModel>) {
    if (addedFoods.isEmpty()) {
      progress_bar_pizza.visibility = View.GONE
      empty_watchlist_textview.visibility = View.VISIBLE
      dishes_recyclerview.visibility = View.GONE
    } else {
      progress_bar_pizza.visibility = View.GONE
      empty_watchlist_textview.visibility = View.GONE
      dishes_recyclerview.visibility = View.VISIBLE

      foodAdapter.setDishes(addedFoods)
    }
  }

  private fun showError() {
    Toast.makeText(requireContext(), "Failed to load dishes", Toast.LENGTH_SHORT).show()
  }
}