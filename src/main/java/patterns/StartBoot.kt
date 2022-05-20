package patterns

import patterns.dynamicproxy.SubjectDynamicProxy
import patterns.staticproxy.SubjectImpl
import patterns.staticproxy.SubjectStaticProxy
import java.lang.reflect.Proxy

class StartBoot {

    companion object {
        @JvmStatic
        fun main(args: Array<String>){

            //静态代理
            var target = SubjectImpl()
           /* var targetProxy = SubjectStaticProxy(target)
            targetProxy.request()
*/
            //动态代理
            var targetDynamicProxy = Proxy.newProxyInstance(
                ISubject::class.java.classLoader,
                arrayOf<Class<*>>(ISubject::class.java),
                SubjectDynamicProxy(target)) as ISubject
            targetDynamicProxy.request()
        }
    }

}