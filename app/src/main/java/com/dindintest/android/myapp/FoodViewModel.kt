package com.dindintest.android.myapp

import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.*
import com.dindintest.android.myapp.data.FoodModel
import com.dindintest.android.myapp.data.FoodRepository
import com.dindintest.android.myapp.data.FoodState

class FoodViewModel(
    initialState: FoodState,
    private val foodRepository: FoodRepository
) : BaseMvRxViewModel<FoodState>(initialState, debugMode = true) {

  val errorMessage = MutableLiveData<String>()
  init {
    setState {
      copy(pizzas = Loading())
        copy(sushis = Loading())
        copy(drinks = Loading())
    }
    foodRepository.getAllPizzas()
        .execute {
          copy(pizzas = it)
        }
      foodRepository.getAllDrinks()
          .execute {
              copy(drinks = it)
          }
      foodRepository.getAllSushi()
          .execute {
              copy(sushis = it)
          }

  }

    fun getItemsInCart():MutableList<FoodModel>{
        return foodRepository.inCart
    }

  fun addPizza(dishId: Long) {
    withState { state ->
      if (state.pizzas is Success) {
        val index = state.pizzas.invoke().indexOfFirst {
          it.id == dishId
        }

        foodRepository.addPizzaToCart(dishId)
            .execute {
              if (it is Success) {
                copy(
                    pizzas = Success(
                        state.pizzas.invoke().toMutableList().apply {
                          set(index, it.invoke())
                        }
                    )
                )
              } else if (it is Fail){
                errorMessage.postValue("Failed to add food to cart")
                copy()
              } else {
                copy()
              }
            }
      }
    }
  }

  fun removePizza(dishId: Long) {
    withState { state ->
      if (state.pizzas is Success) {
        val index = state.pizzas.invoke().indexOfFirst {
          it.id == dishId
        }
        foodRepository.removePizzaFromCart(dishId)
            .execute {
              if (it is Success) {
                copy(
                    pizzas = Success(
                        state.pizzas.invoke().toMutableList().apply {
                          set(index, it.invoke())
                        }
                    )
                )
              } else if (it is Fail){
                errorMessage.postValue("Failed to remove food from cart")
                copy()
              } else {
                copy()
              }
            }
      }
    }
  }

    fun addSushi(dishId: Long) {
        withState { state ->
            if (state.sushis is Success) {
                val index = state.sushis.invoke().indexOfFirst {
                    it.id == dishId
                }

                foodRepository.addSushiToCart(dishId)
                    .execute {
                        if (it is Success) {
                            copy(
                                sushis = Success(
                                    state.sushis.invoke().toMutableList().apply {
                                        set(index, it.invoke())
                                    }
                                )
                            )
                        } else if (it is Fail){
                            errorMessage.postValue("Failed to add food to cart")
                            copy()
                        } else {
                            copy()
                        }
                    }
            }
        }
    }

    fun removeSushi(dishId: Long) {
        withState { state ->
            if (state.sushis is Success) {
                val index = state.sushis.invoke().indexOfFirst {
                    it.id == dishId
                }
                foodRepository.removeSushiFromCart(dishId)
                    .execute {
                        if (it is Success) {
                            copy(
                                sushis = Success(
                                    state.sushis.invoke().toMutableList().apply {
                                        set(index, it.invoke())
                                    }
                                )
                            )
                        } else if (it is Fail){
                            errorMessage.postValue("Failed to remove food from cart")
                            copy()
                        } else {
                            copy()
                        }
                    }
            }
        }
    }


    fun addDrinks(dishId: Long) {
        withState { state ->
            if (state.drinks is Success) {
                val index = state.drinks.invoke().indexOfFirst {
                    it.id == dishId
                }

                foodRepository.addDrinksToCart(dishId)
                    .execute {
                        if (it is Success) {
                            copy(
                                drinks = Success(
                                    state.drinks.invoke().toMutableList().apply {
                                        set(index, it.invoke())
                                    }
                                )
                            )
                        } else if (it is Fail){
                            errorMessage.postValue("Failed to add food to cart")
                            copy()
                        } else {
                            copy()
                        }
                    }
            }
        }
    }

    fun removeDrinks(movieId: Long) {
        withState { state ->
            if (state.drinks is Success) {
                val index = state.drinks.invoke().indexOfFirst {
                    it.id == movieId
                }
                foodRepository.removeDrinksFromCart(movieId)
                    .execute {
                        if (it is Success) {
                            copy(
                                drinks = Success(
                                    state.drinks.invoke().toMutableList().apply {
                                        set(index, it.invoke())
                                    }
                                )
                            )
                        } else if (it is Fail){
                            errorMessage.postValue("Failed to remove food from cart")
                            copy()
                        } else {
                            copy()
                        }
                    }
            }
        }
    }

  companion object : MvRxViewModelFactory<FoodViewModel, FoodState> {
    override fun create(viewModelContext: ViewModelContext,
        state: FoodState
    ): FoodViewModel? {
      val watchlistRepository = viewModelContext.app<FoodApp>().watchlistRepository
      return FoodViewModel(state, watchlistRepository)
    }
  }
}