package patterns.staticproxy

import patterns.ISubject

class SubjectImpl: ISubject {

    override fun request() {

        println("real client")
    }
}