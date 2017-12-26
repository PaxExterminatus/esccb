package framework.router

import framework.call
import framework.system.SysModule
import javax.servlet.http.HttpServletRequest

class ModuleRouter(private val req: HttpServletRequest)
{
    fun moduleGet(): SysModule
    {
        if (call.module == "sync")
            return application.implementation.ems.SyncModule()

        if (call.module == "status")
            return application.implementation.system.StatusModule()

        return framework.system.ErrorModule()
    }
}