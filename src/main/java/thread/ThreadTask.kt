package thread

open class ThreadTask : Thread() {

    init {
        println("current name:${Thread.currentThread().name}")
        println("current name:${this.name}")
    }

    override fun run() {

    }
}