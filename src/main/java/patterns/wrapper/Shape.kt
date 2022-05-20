package patterns.wrapper

interface Shape {

    fun onDraw()
}

class Circle: Shape{
    override fun onDraw() {
        println("draw a circle")
    }

}

/**
 * 构造函数中val表示该属性初始化为对应的构造函数参数，就不用再再类中定一个属性去初始化
 */
open class ShapeDecorator(private val shape: Shape): Shape {
    override fun onDraw() {
        shape.onDraw()
    }

}

class AShapeDecorator(shape: Shape): ShapeDecorator(shape) {
    override fun onDraw() {
        super.onDraw()
        println("decorator draw a shape")
    }
}

fun main(args: Array<String>) {
    var circle = Circle()
    circle.onDraw()

    var aCircle = AShapeDecorator(circle)
    aCircle.onDraw()
}