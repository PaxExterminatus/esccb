package framework.module

import framework.*
import framework.view.View

abstract class BaseModule {
    abstract val moduleName: String //todo имя модуля из имени класса
    abstract val workNames: Array<String> //todo получать список действий из методов с именем xxxWork

    abstract fun workRouter(work: String, queryString: String)

    protected fun view(viewName: String) {
        View(viewName)
    }

    fun exception(exceptionCode: String, message: String = "")
    {
        when (exceptionCode)
        {
            "WNS" ->
            {
                document.add("<h1>Work not supported!</h1>")
                document.add("module: ${call.module}, work: ${call.work}")
                document.add("message: $message")
            }
            "MNS" ->
            {
                document.add("<h1>Module not supported!</h1>")
                document.add("module: ${call.module}, work: ${call.work}")
                document.add("message: $message")
            }
            else ->
            {
                document.add("<h1>Unknown error!</h1>")
                document.add("module: ${call.module}, work: ${call.work}")
                document.add("message: $message")
            }
        }
    }
}