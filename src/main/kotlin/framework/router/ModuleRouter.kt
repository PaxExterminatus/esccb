package framework.router

import framework.system.SysModule
import javax.servlet.http.HttpServletRequest

class ModuleRouter(val req: HttpServletRequest)
{
    fun moduleGet(): SysModule
    {
        if (req.requestURI === "/esccb/ems/sync/copy")
            return application.implementation.ems.SyncModule()

        if (req.requestURI === "/esccb/system/status/show")
            return application.implementation.system.StatusModule()

        return framework.system.ErrorModule()
    }
}