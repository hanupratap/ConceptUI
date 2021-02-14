package com.dindintest.android.myapp.paymentFragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.dindintest.android.myapp.R
import com.dindintest.android.myapp.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.view.*
import kotlinx.android.synthetic.main.fragment_order.view.*


class CartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        val fragmentList = arrayListOf(
            OrderFragment(),
            OrderHistory(),
            UserInformation()
        )

        val frag_names = arrayListOf(
            "Cart",
            "Orders",
            "Information"
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.mviewPager_cart.adapter = adapter

        TabLayoutMediator(view.tabLayout_cart, view.mviewPager_cart) { tab, position ->
            tab.text = frag_names[position]
        }.attach()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cart_fab.setOnClickListener {
            Log.d(TAG, "WALLET: Wallet button pressed")
        }
        back_btn.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

}