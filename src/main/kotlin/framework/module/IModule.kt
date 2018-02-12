package framework.module

import framework.view.View

interface IModule {

    val moduleName: String //todo имя модуля из имени класса
    val workNames: Array<String> //todo получать список действий из методов с именем xxxWork
    fun workRouter(work: String, queryString: String)

    fun view(viewName: String) = View(viewName)
}