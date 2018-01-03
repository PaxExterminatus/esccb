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
        //Сброс
        document.clear()
        call.clear()
        //Инициализация
        call.initCall(req)
        settings.load(servletContext.getRealPath("/"))
        document.templateTopBuild()

        //Передаем управление модулю
        ModuleRouter(req).moduleGet().workRouter(call.work,"")

        //Вывод ответа отмета
        resp.writer.write(document.content())
    }
}