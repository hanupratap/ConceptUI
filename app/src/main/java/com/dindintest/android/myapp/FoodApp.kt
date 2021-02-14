package com.dindintest.android.myapp

import android.app.Application
import com.dindintest.android.myapp.data.FoodRepository

class FoodApp : Application() {
  val watchlistRepository by lazy {
    FoodRepository()
  }


}