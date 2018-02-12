package framework.exception

import framework.call

open class FWException: Exception() {
    override val message: String = ""
    open val title = "Unknown Exception"

    val module = call.module
    val work = call.work
}

class FWEWorkNotSupported: FWException() {
    override val title = "Work not supporte"
    override val message: String = ""
}

class FWEModuleNotSupported: FWException() {
    override val title = "Module not supported"
    override val message: String = ""
}