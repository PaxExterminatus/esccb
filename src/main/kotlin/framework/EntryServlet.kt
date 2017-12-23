package framework

import framework.router.ModuleRouter
import javax.servlet.http.HttpServlet
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import java.io.File
import java.io.InputStream

@WebServlet(name = "EntryServlet", value = ["/"])
class EntryServlet: HttpServlet() {
    override fun service(req: HttpServletRequest, resp: HttpServletResponse)
    {
        document.context = ""
        document.contextDebug = ""

        val application= servletContext


        call.globalInit(req)
        val module = ModuleRouter(req).moduleGet()
        module.workRouter(call.work,"")
        document.context += "v1<br>"
        document.context += "Module Name: ${module.moduleName}<br>"

        val file = application.getRealPath("/settings/app.json")

        val inputString = File(file).inputStream().bufferedReader().use { it.readText() }
        document.context += inputString

        resp.writer.write(document.content())
    }
}