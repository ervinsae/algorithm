package ervin

import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.coroutineContext

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


    fun treatOrTrick(isTrick: Boolean, extra: ((Int) -> String)?): () -> Unit {
        if (isTrick) {
            return { println("return function1")}
        } else {
            if (extra != null) {
                println(extra(5))
            }
            return { println("return function2")}
        }
    }





}

fun printFormat(message: String) {
    println(Thread.currentThread().name + "--" + message)
}


suspend fun main(args: Array<String>) {
    /*val result = "testLet".also {
        println(it.length)
        1000
    }
    println(result)*/

    /*val cls = Test()
    val treatFunction = cls.treatOrTrick(true, null)
    val trickFunction = cls.treatOrTrick(false){ "$it only" }

    repeat(4) {
        println("this is the $it run")
        treatFunction()
    }

    trickFunction()*/

    println(Thread.currentThread().name + "--" + 1)
    try {
        coroutineScope {
            println(Thread.currentThread().name + "--" + 2)
            launch {
                println(Thread.currentThread().name + "--" + 3)
                launch(Dispatchers.IO) {
                    println(Thread.currentThread().name + "--" + 4)
                    delay(100)
                    throw ArithmeticException("i am wrong!")
                }
            }

            val job = launch {
                println(Thread.currentThread().name + "--" + 5)
            }
            try {
                println(Thread.currentThread().name + "--" + 6)
                job.join()
            }catch (e: Exception) {
                println(Thread.currentThread().name + "--" + 7)
            }
        }
    } catch (e: Exception) {
        println(Thread.currentThread().name + "--" + 8 + e.message)
    }

    println(Thread.currentThread().name + "--" + 9)

    withContext(Dispatchers.IO){
        println(Thread.currentThread().name + "--" + 10)
    }

    GlobalScope.launch {
        delay(1000)
        println(Thread.currentThread().name + "--" + 11)
    }

}
