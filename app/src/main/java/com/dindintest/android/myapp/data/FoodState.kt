
package com.dindintest.android.myapp.data

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.dindintest.android.myapp.data.FoodModel

data class FoodState(
    val pizzas: Async<List<FoodModel>> = Uninitialized,
    val sushis: Async<List<FoodModel>> = Uninitialized,
    val drinks: Async<List<FoodModel>> = Uninitialized
) : MvRxState