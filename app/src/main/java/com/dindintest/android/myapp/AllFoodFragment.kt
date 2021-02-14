package com.dindintest.android.myapp

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.airbnb.mvrx.*
import com.dindintest.android.myapp.adapters.IntroSlideAdapter
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
  val fadeOut = AlphaAnimation(1f, 0f)


  private val introSliderAdapter = IntroSlideAdapter(
    listOf(
      R.drawable.burger,
      R.drawable.range,
      R.drawable.pizza
    )
  )

  override fun invalidate() {

      val num = foodViewModel.getItemsInCart().size
    
      if(num==0) {
        counter_fab.visibility = View.GONE
      }
      else{
        counter_fab.visibility = View.VISIBLE
        while(num>counter_fab.count && counter_fab.count!=num)
          counter_fab.increase()

        while(num<counter_fab.count && counter_fab.count!=num)
          counter_fab.decrease()
      }

    }

  private fun setupIndicators(mview: View){
    val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
    val layoutParams: LinearLayout.LayoutParams =
      LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

    layoutParams.setMargins(8, 0, 8, 0)

    for(i in indicators.indices){
      indicators[i] = ImageView(requireContext())
      indicators[i].apply {
        this?.setImageDrawable(
          ContextCompat.getDrawable(
            requireContext(),
            R.drawable.indicator_inactive
          )
        )
        this?.layoutParams = layoutParams
      }
      mview.indicatorContainer?.addView(indicators[i])
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

    view.introSlider_viewPager.adapter = introSliderAdapter

    setupIndicators(view)

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

    counter_fab.setOnClickListener{
      val mFragment = CartFragment()
      val transaction = requireFragmentManager().beginTransaction()
      transaction.add(R.id.fragment_container, mFragment)
      transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
      transaction.addToBackStack(null)
      transaction.commit()
    }

  }


}