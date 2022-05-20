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


//private修饰的无参构造函数，不能直接被new实例化，KT中单例的静态内部类写法
class Person private constructor(){

    companion object {
        fun get() = Holder.instance
    }

    constructor(name: String): this() {
        print(name)
    }

    //init做为主构造函数的一部分，委托给主构造函数会作为次构造函数的第一天语句，因此所有init中的代码都会在次构造函数之前执行
    init {
        print("init")
    }

    private object Holder {
        val instance = Person("bcd")
    }
}

//密封类
/**
 * 密封类所有的子类必现和密封类声明在同一个.kt文件中
 * 类似枚举类，在使用when表达式的时候可以不使用else，因为已经覆盖类所有的情况
 */
sealed class Expr {
    object NotANumber: Expr()
    class Const(val number: Double): Expr()
    class Sum(val e1: Expr, val e2: Expr): Expr()
}


fun main(args: Array<String>) {
    var person = Person("abc")
    var person1 = Person.get()
}