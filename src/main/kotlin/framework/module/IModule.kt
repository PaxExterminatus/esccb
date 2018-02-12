package framework.module

import framework.*
import framework.view.View

interface IModule {

    val moduleName: String //todo имя модуля из имени класса
    val workNames: Array<String> //todo получать список действий из методов с именем xxxWork
    fun workRouter(work: String, queryString: String)

    fun view(viewName: String) = View(viewName)

    fun exception(exp: FWException, message: String = "") {
        fun errorMessage(title: String, message: String) {
            document.add("<h1>$title!</h1>")
            document.add("module: ${call.module}, work: ${call.work}")
            document.add(message)
        }

        when (exp) {
            is FWException.WorkNotSupported -> errorMessage("Work not supported", message)
            is FWException.ModuleNotSupported -> errorMessage("Module not supported", message)
            is FWException.Unknown -> errorMessage("Unknown error", message)
        }
    }
}