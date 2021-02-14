package com.dindintest.android.myapp.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.lifecycleAwareLazy
import com.dindintest.android.myapp.foodtypes.DrinkFragment
import com.dindintest.android.myapp.foodtypes.PizzaFragment
import com.dindintest.android.myapp.foodtypes.SushiFragment

class ViewPagerAdapter(
    list: ArrayList<BaseMvRxFragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    private val flist = list;

    override fun getItemCount(): Int {
        return flist.size
    }

    override fun createFragment(position: Int): Fragment {
        return flist[position]
    }
}