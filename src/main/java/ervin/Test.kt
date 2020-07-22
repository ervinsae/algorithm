package ervin

class Test{

    fun test() {

        println("Hello, world!!!")

        val num: Int = 128;
        val a:Int? = num
        val b:Int? = num
        println(a == b)
        print(a === b)

        val (number,value) = 3 to "a"

        val m = run {
            println("run")
            return@run  3
        }
    }

    fun returnTest(){

        var arrayA = Array(5,{a -> a})

        arrayA.forEach { a ->
            if (a == 3){
                return
            }
            print(a)
        }

        print("end")
    }
}

fun main(args: Array<String>) {
    val result = "testLet".also {
        println(it.length)
        1000
    }
    println(result)
}
