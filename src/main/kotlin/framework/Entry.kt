package framework

import framework.exception.FWException
import framework.exception.FWExceptionModule
import framework.router.ModuleRouter
import javax.servlet.http.HttpServlet
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "Entry", value = ["/"])
class Entry : HttpServlet() {
    override fun service(req: HttpServletRequest, resp: HttpServletResponse)
    {
        //Инициализация
        document.clear()
        settings.fill(servletContext.getRealPath("/"))
        call.fill(req)

        //Передаем управление модулю
        try {
            ModuleRouter().moduleGet().workRouter(call.work,"")
        } catch (e: FWException) {
            FWExceptionModule(e)
        }

        //Вывод ответа отмета
        resp.writer.write(document.content())
    }
}