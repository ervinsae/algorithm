package ervin.coroutine

import kotlinx.coroutines.*
import sun.rmi.server.Dispatcher
import java.sql.DriverManager.println
import java.util.concurrent.Executor
import java.util.concurrent.Executors


suspend fun main(args: Array<String>) {

/*    //runBlockingTest()
    //delay(100)
    printFormat("start")

    GlobalScope.launch(Executors.newSingleThreadExecutor().asCoroutineDispatcher()) {
        testSuspend()
   }

    GlobalScope.launch(Executors.newSingleThreadExecutor().asCoroutineDispatcher()) {
        testSuspend2()
    }

    runBlockingTest()
    printFormat("end")*/

    printFormat("AAA")

    /**
     * 测试一个线程中多个协程的运行逻辑
     */
    /*var dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    CoroutineScope(dispatcher).launch(start = CoroutineStart.DEFAULT) {
        test()
    }


    //在同一个线程中
    dispatcher.executor.execute {
        printFormat("thread start")
        Thread.sleep(5000)
        printFormat("thread end")
    }

    GlobalScope.launch(dispatcher, start = CoroutineStart.DEFAULT) {
        printFormat("end")
    }*/
    //test()


    /**
     * 单个协程运行逻辑
     */
    /*CoroutineScope(Dispatchers.Default).launch {
        delay(110)
        printFormat("coroutine launch")
    }*/


    /**
     * async用法
     */
    /*val def = CoroutineScope(Dispatchers.Default).async {
        delay(100)
        printFormat("coroutine async")
        "Hello coroutine"
    }

    printFormat(def.await())*/

    /**
     * 测试async并发
     */

    asyncConcurrent()

    /* Thread.sleep(100)*/
    printFormat("BBB")


}

/**
 * ------------------同一个线程中同一个协程---------------
 */
suspend fun test() {

    printFormat("A1")
    testB()
    printFormat("B1")
}

suspend fun testB() {
    printFormat("A2")
    testC()
    delay(100)
    printFormat("B2")
}

suspend fun testC() {
    printFormat("A3")
    delay(100)
    printFormat("B3")
}

/**
 * -----------------同一个线程中不同协程-------------------
 */
suspend fun test2() {
    GlobalScope.launch {
        printFormat("A1")
        test2B()
        printFormat("B1")
    }
}

suspend fun test2B() {
    GlobalScope.launch {
        printFormat("A2")
        test2C()
        delay(100)
        printFormat("B2")
    }
}

suspend fun test2C() {
    var job = GlobalScope.launch {
        printFormat("A3")
        delay(1000)
        printFormat("B3")
    }
}

/**
 * -----------------阻塞线程的协程-------------------
 */
suspend fun runBlockingTest() {
    println("start")

    runBlocking {
        //delay(3000)
        printFormat("block")
    }

    var gJob = GlobalScope.launch {
        printFormat("new launch 1")
        var job = launch {
            delay(1000)
            printFormat("new launch 2")
        }

        //job.join()
    }

    gJob.join()
    println("end")

}

/**
 * -----------------async并发----------------
 *
 *  1.Deferred集合还可以使用awaitAll()等待全部完成；
 *  2.如果Deferred不执行await()则async内部抛出的异常不会被logCat或tryCatch捕获, 但是依然会导致作用域取消和异常崩溃; 但当执行await时异常信息会重新抛出。
 *  3.惰性并发，如果将async函数中的启动模式设置为CoroutineStart.LAZY懒加载模式时则只有调用Deferred对象的await时(或者执行async.satrt())才会开始执行异步任务。
 */
suspend fun asyncConcurrent() {

    val startTime = System.currentTimeMillis()
    var endTime = 0L
    val job = CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher()).async {

        var job1 = async {
            delay(3000)
            printFormat("job1")
            true
        }

        var job2 = async {
            delay(2000)
            printFormat("job2")
            true
        }

        var job3 = async {
            delay(1000)
            printFormat("job3")
            true
        }

        /*while (true) {
            //delay(1)
        }*/

        printFormat("job while")
        if (job1.await() && job2.await() && job3.await()) {
            printFormat("all done")
            //break
        }

        endTime = System.currentTimeMillis()
        System.currentTimeMillis()
    }

    job.join()
    //job.await()
    printFormat("total ms: ${endTime - startTime}")
}

suspend fun testSuspend() {

    /*GlobalScope.launch(Dispatchers.Default) {
        var sum = 0
        for (i in 1..10000) {
            //delay(100)
            sum += i
        }
        printFormat("suspend fun")
    }*/
    delay(10000)
    printFormat("start1")
    printFormat("suspend fun1")

}

suspend fun testSuspend2() {
    printFormat("start2")
    CoroutineScope(Dispatchers.IO).launch {

        //delay(1000)
        printFormat("suspend fun2")
    }
}

fun printFormat(message: String) {
    println(Thread.currentThread().name + "--" + message)
}