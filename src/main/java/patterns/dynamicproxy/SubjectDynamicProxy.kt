package patterns.dynamicproxy

import patterns.ISubject
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class SubjectDynamicProxy(target: Any): InvocationHandler {

    var subject : Any = target

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        before()
        var result = method!!.invoke(subject, *(args ?: arrayOfNulls(0)))
        after()
        return result
    }

    private fun after() {
        println("proxy client do something after")
    }

    private fun before() {
        println("proxy client do something before")
    }

}