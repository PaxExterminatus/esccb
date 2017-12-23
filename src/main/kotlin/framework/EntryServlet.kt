package framework

import framework.router.ModuleRouter
import javax.servlet.http.HttpServlet
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "EntryServlet", value = ["/"])
class EntryServlet: HttpServlet() {
    override fun service(req: HttpServletRequest, resp: HttpServletResponse)
    {
        //Блок инициализации и сброса
        document.context = ""
        document.contextDebug = ""
        call.initCall(req)
        ways.initWays(servletContext.getRealPath("/"))

        //Определяем вызываемый модуль
        val module = ModuleRouter(req).moduleGet()
        module.workRouter(call.work,"")

        //Формирование отмета
        resp.writer.write(document.content())
    }
}