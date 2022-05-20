package patterns.staticproxy

import patterns.ISubject

class SubjectStaticProxy(target: ISubject) : ISubject {
    var iSubject: ISubject? = target

    override fun request() {
        before()
        iSubject?.request()
        after()
    }

    private fun after() {
        println("proxy client do something after")
    }

    private fun before() {
        println("proxy client do something before")
    }
}