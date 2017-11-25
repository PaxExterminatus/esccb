package framework.router

import framework.system.SysModule
import javax.servlet.http.HttpServletRequest

class ModuleRouter(val req: HttpServletRequest)
{
    fun moduleGet(): SysModule
    {
        if (req.requestURI === "/esccb/ems/sync/copy") return application.implementation.ems.SyncModule()

        return framework.system.ErrorModule()
    }
}