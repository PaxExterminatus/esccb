package framework

import framework.router.ModuleRouter
import javax.servlet.http.HttpServlet
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "EntryServlet", value = "/*")
class EntryServlet: HttpServlet() {
    override fun service(req: HttpServletRequest, resp: HttpServletResponse)
    {
        document.context = ""
        document.contextDebug = ""

        call.globalInit(req)
        val module = ModuleRouter(req).moduleGet()
        module.workRouter(call.work,"")
        document.context += "Module Name: ${module.moduleName}<br>"

        resp.writer.write(document.content())
    }
}