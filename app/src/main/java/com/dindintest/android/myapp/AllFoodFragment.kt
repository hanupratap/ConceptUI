package com.dindintest.android.myapp

import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.airbnb.mvrx.*
import com.dindintest.android.myapp.adapters.ViewPagerAdapter
import com.dindintest.android.myapp.foodtypes.DrinkFragment
import com.dindintest.android.myapp.foodtypes.PizzaFragment
import com.dindintest.android.myapp.foodtypes.SushiFragment
import com.dindintest.android.myapp.paymentFragments.CartFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_all_dishes.*
import kotlinx.android.synthetic.main.fragment_all_dishes.view.*

class AllFoodFragment : BaseMvRxFragment() {
  private val foodViewModel:FoodViewModel by activityViewModel()

  override fun invalidate() {
    withState(foodViewModel) { state ->
      val num = foodViewModel.getItemsInCart().size
      if(num==0)
        counter_fab.visibility = View.GONE
      else{
        counter_fab.visibility = View.VISIBLE
        while(num>counter_fab.count && counter_fab.count!=num)
          counter_fab.increase()

        while(num<counter_fab.count && counter_fab.count!=num)
          counter_fab.decrease()
      }

    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_all_dishes, container, false)

    val fragmentList = arrayListOf(
      PizzaFragment(),
      SushiFragment(),
      DrinkFragment()
    )

    val frag_names = arrayListOf(
      "Pizza",
      "Sushi",
      "Drinks"
    )

    val adapter = ViewPagerAdapter(
      fragmentList,
      requireActivity().supportFragmentManager,
      lifecycle
    )

    view.mviewPager.adapter = adapter
    TabLayoutMediator(view.tabLayout, view.mviewPager) { tab, position ->
      tab.text = frag_names[position]
    }.attach()

    return view

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
//    foodAdapter = FoodAdapter(object : FoodAdapter.WatchlistListener {
//      override fun addToCart(movieId: Long) {
//        // call ViewModel to add movie to watchlist
//        foodViewModel.addFood(movieId)
//      }
//
//      override fun removeFromCart(movieId: Long) {
//        // call ViewModel to remove movie from watchlist
//        foodViewModel.removeFoodFromCart(movieId)
//      }
//    })
//    all_movies_recyclerview.adapter = foodAdapter
//    foodViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
//      Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
//    })

    counter_fab.setOnClickListener{
      val mFragment = CartFragment()
      val transaction = requireFragmentManager().beginTransaction()
      transaction.add(R.id.fragment_container, mFragment)
      transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
      transaction.addToBackStack(null)
      transaction.commit()
    }

  }

//  override fun invalidate() {
//    withState(foodViewModel) { state ->
//      when (state.dishes) {
//        is Loading -> {
//          progress_bar.visibility = View.VISIBLE
//          all_movies_recyclerview.visibility = View.GONE
//        }
//        is Success -> {
//          progress_bar.visibility = View.GONE
//          all_movies_recyclerview.visibility = View.VISIBLE
//          foodAdapter.setMovies(state.dishes.invoke())
//        }
//        is Fail -> {
//          Toast.makeText(requireContext(), "Failed to load all dishes", Toast.LENGTH_SHORT).show()
//        }
//      }
//    }
//  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.cartlist, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.cartlist) {
      val mFragment = FoodFragment()
      val transaction = requireFragmentManager().beginTransaction()
      transaction.replace(R.id.fragment_container, mFragment)
      transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
      transaction.addToBackStack(null)
      transaction.commit()
    }
    return super.onOptionsItemSelected(item)
  }
}