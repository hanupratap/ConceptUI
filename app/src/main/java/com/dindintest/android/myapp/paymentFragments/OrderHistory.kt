package com.dindintest.android.myapp.paymentFragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.dindintest.android.myapp.FoodViewModel
import com.dindintest.android.myapp.R


class OrderHistory : BaseMvRxFragment() {
    private val foodViewModel: FoodViewModel by activityViewModel()

    override fun invalidate() {
        withState(foodViewModel){
            Log.d(TAG, "Order History: Yet to implement")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_history, container, false)
    }


}