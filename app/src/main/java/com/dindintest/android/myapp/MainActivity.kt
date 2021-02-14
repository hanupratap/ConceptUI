package com.dindintest.android.myapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dindintest.android.myapp.foodtypes.PizzaFragment

class MainActivity : AppCompatActivity() {



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

      val allDishesFragment = AllFoodFragment()

    val transaction = supportFragmentManager.beginTransaction()
      transaction.replace(R.id.fragment_container, allDishesFragment)
      transaction.commit()
  }


}
