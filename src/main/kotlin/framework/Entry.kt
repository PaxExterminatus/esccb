package framework

import framework.router.ModuleRouter
import framework.view.View
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
        ModuleRouter().moduleGet().workRouter(call.work,"")

        val v = View("hello.html")

        //Вывод ответа отмета
        resp.writer.write(document.content())
    }
}