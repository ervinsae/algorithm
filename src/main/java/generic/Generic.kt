package generic

//kotlin泛型

interface Production<out T> {
    fun produce(): T
}


interface Consumer<in T> {
    fun consume(item: T)
}


interface ProductionConsumer<T> {
    fun produce(): T
    fun consume(item: T)
}



open class Food
open class FastFood : Food()
class Burger : FastFood()



//汉堡提供者
class FoodStore: Production<Food> {
    override fun produce(): Food {
        return Food()
    }
}

class FastFoodStore: Production<FastFood> {
    override fun produce(): FastFood {
        return FastFood()
    }

}

class BurgerStore: Production<Burger> {
    override fun produce(): Burger {

        return Burger()
    }
}

/**
*
* -----------对于out泛型：使用子类泛型的对象赋值给使用父类泛型对象------------
*
*/
val p1: Production<Food> = FoodStore()
val p2: Production<Food> = FastFoodStore()
val p3: Production<Food> = BurgerStore()


//val p4: Production<Burger> = FoodStore() error


//汉堡消费者
class Everybody : Consumer<Food> {
    override fun consume(item: Food) {
        println("Eat food")
    }
}

class ModernPeople : Consumer<FastFood> {
    override fun consume(item: FastFood) {
        println("Eat fast food")
    }
}

class American : Consumer<Burger> {
    override fun consume(item: Burger) {
        println("Eat burger")
    }
}

/**
 * ------------对于in泛型：使用父类泛型的对象赋值给使用子类泛型的对象------------
 **/
val consumer1 : Consumer<Burger> = Everybody()
val consumer2 : Consumer<Burger> = ModernPeople()
val consumer3 : Consumer<Burger> = American()

//val consumer4: Consumer<Food> = American() error