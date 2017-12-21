package application.implementation.system

import framework.system.SysModule

class StatusModule: SysModule()
{
    override val systemName: String = "system"
    override val moduleName: String = "status"
    override val actionNames: Array<String> = arrayOf("show")

    override fun workRouter(work: String, queryString: String) {

    }
}