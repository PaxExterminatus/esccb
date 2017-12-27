package framework.router

import framework.call
import framework.system.SysModule
import javax.servlet.http.HttpServletRequest

class ModuleRouter(private val req: HttpServletRequest)
{
    fun moduleGet(): SysModule
    {
        if (call.module == "ems")
            return application.implementation.ems.EmsModule()

        if (call.module == "status")
            return application.implementation.system.StatusModule()

        return framework.system.ErrorModule()
    }
}