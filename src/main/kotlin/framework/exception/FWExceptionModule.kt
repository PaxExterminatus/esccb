package framework.exception

import framework.document

class FWExceptionModule(e: FWException) {
    init {
        document.add("<h1>${e.title}</h1>")
        document.add("module: ${e.module}, work: ${e.work}")
        document.add(e.message)
    }
}