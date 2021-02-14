
package com.dindintest.android.myapp.data

import com.dindintest.android.myapp.foodtypes.PizzaFragment
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class FoodRepository {

   var inCart = mutableListOf<FoodModel>()

   private var pizzas = mutableListOf<FoodModel>()

    private var sushi = mutableListOf<FoodModel>()

    private var drinks = mutableListOf<FoodModel>()

    fun getAllDrinks() = Observable.fromCallable<List<FoodModel>> {
        // Mocking Network Request
        Thread.sleep(1500)
        drinks.clear()
        drinks.addAll(listOf(
            FoodModel(
                1835,
                "Beer",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Cs%C3%ADki_s%C3%B6r_a_II._Budapesti_Sz%C3%A9kely_B%C3%A1lon_%281%29.JPG/800px-Cs%C3%ADki_s%C3%B6r_a_II._Budapesti_Sz%C3%A9kely_B%C3%A1lon_%281%29.JPG",
                15,
                false
            ),
            FoodModel(
                1836,
                "Coca Cola",
                "https://thumbor.forbes.com/thumbor/fit-in/1200x0/filters%3Aformat%28jpg%29/https%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F1189255149%2F0x0.jpg",
                15,
                false
            ),
            FoodModel(
                1837,
                "Pepsi",
                "https://cdn-a.william-reed.com/var/wrbm_gb_food_pharma/storage/images/publications/food-beverage-nutrition/foodnavigator-asia.com/article/2019/10/21/pepsico-s-regional-discomfort-analyst-suggests-deeper-reasoning-behind-brand-s-philippines-and-indonesia-cutbacks/10259416-1-eng-GB/PepsiCo-s-regional-discomfort-Analyst-suggests-deeper-reasoning-behind-brand-s-Philippines-and-Indonesia-cutbacks.jpg",
                15,
                false
            ),
            FoodModel(
                1838,
                "Chocolate",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/00/Chocolate_and_croissant.jpg/800px-Chocolate_and_croissant.jpg",
                15,
                false
            )
        ))
        drinks

    }.subscribeOn(Schedulers.io())




    fun getAllSushi() = Observable.fromCallable<List<FoodModel>> {
        // Mocking Network Request
        Thread.sleep(1500)
        sushi.clear()
        sushi.addAll(listOf(
            FoodModel(
                1035,
                "The egoist",
                "https://content3.jdmagicbox.com/comp/def_content/japanese_restaurants/default-japanese-restaurants-1.jpg",
                15,
                false
            ),
            FoodModel(
                1036,
                "Califronia",
                "https://www.kikkoman.com/homecook/search/recipe/img/00005163.jpg",
                15,
                false
            ),
            FoodModel(
                1037,
                "Special One",
                "https://www.nippon.com/en/ncommon/contents/japan-data/169591/169591.jpg",
                15,
                false
            ),
            FoodModel(
                1038,
                "Japanese",
                "https://media-cdn.tripadvisor.com/media/photo-s/13/bb/08/54/sushi-platter.jpg",
                15,
                false
            )

        ))
        sushi

    }.subscribeOn(Schedulers.io())

    fun getAllPizzas() = Observable.fromCallable<List<FoodModel>> {
        // Mocking Network Request
        Thread.sleep(1500)
        pizzas.clear()
        pizzas.addAll(listOf(
            FoodModel(
                1235,
                "Hawaiin",
                "https://i0.wp.com/www.eatthis.com/wp-content/uploads/2019/01/spicy-hawaiian-pizza.jpg?fit=1200%2C879&ssl=1",
                15,
                false
            ),
            FoodModel(
                1236,
                "Deluxe",
                "https://www.killingthyme.net/wp-content/uploads/2020/09/veggie-deluxe-pizza-5.jpg",
                15,
                false
            ),
            FoodModel(
                1237,
                "Pepperoni",
                "https://www.moulinex-me.com/medias/?context=bWFzdGVyfHJvb3R8MTQzNTExfGltYWdlL2pwZWd8aDM2L2g1Mi8xMzA5NzI3MzI2MjExMC5qcGd8N2MxZDhmNmI5ZTgzZDZlZWQyZGQ4YjFlZjUyNDlkMTExYjdkZDdlZmFkY2I0N2NmNjljOGViNmExZjIyMDU4Yw",
                15,
                false
            ),
            FoodModel(
                1238,
                "Paneer Pizza",
                "https://www.archanaskitchen.com/images/archanaskitchen/1-Author/Pooja_Thakur/Tandoori_Paneer_Tikka_Skillet_Pizza.jpg",
                15,
                false
            )
        ))
        pizzas

    }.subscribeOn(Schedulers.io())


    //Add Drinks to Cart
    fun addDrinksToCart(foodId: Long): Observable<FoodModel> {
        return Observable.fromCallable {
            val item = drinks.first { food -> food.id == foodId }
            inCart.add(item)
            item.copy(incart = true)
        }
    }

    // Remove Drinks from Cart
    fun removeDrinksFromCart(foodId: Long): Observable<FoodModel> {
        return Observable.fromCallable {
            val item = drinks.first { food -> food.id == foodId }
            inCart.remove(item)
            item.copy(incart = false)
        }
    }

    //Add Sushi to Cart
    fun addSushiToCart(foodId: Long): Observable<FoodModel> {

        return Observable.fromCallable {
            val item = sushi.first { food -> food.id == foodId }
            inCart.add(item)
            item.copy(incart = true)
        }
    }

    // Remove Sushi from Cart
    fun removeSushiFromCart(foodId: Long): Observable<FoodModel> {
        return Observable.fromCallable {
            val item = sushi.first { food -> food.id == foodId }
            inCart.remove(item)
            item.copy(incart = false)
        }
    }




  // add method to add food to cart
  fun addPizzaToCart(foodId: Long): Observable<FoodModel> {

    return Observable.fromCallable {
      val item = pizzas.first { food -> food.id == foodId }
       inCart.add(item)
      item.copy(incart = true)
    }
  }

  // add method to remove a food from cart
  fun removePizzaFromCart(foodId: Long): Observable<FoodModel> {
    return Observable.fromCallable {
      val item = pizzas.first { food -> food.id == foodId }
        inCart.remove(item)
      item.copy(incart = false)
    }
  }

}