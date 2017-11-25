package framework.request

import framework.system.SysModule
import javax.servlet.http.HttpServletRequest

class Router(val req: HttpServletRequest)
{
    fun moduleGet(): SysModule
    {
        if (req.requestURI === "/esccb/ems/sync/copy") return application.implementation.ems.SyncModule()

        return framework.system.ErrorModule()
    }
}