package framework.router

import application.implementation.EmsModule
import application.implementation.StatusModule
import framework.call
import framework.system.SysModule
import javax.servlet.http.HttpServletRequest

class ModuleRouter(private val req: HttpServletRequest)
{
    fun moduleGet(): SysModule
    {
        if (call.module == "ems")
            return EmsModule()

        if (call.module == "status")
            return StatusModule()

        return framework.system.ErrorModule()
    }
}