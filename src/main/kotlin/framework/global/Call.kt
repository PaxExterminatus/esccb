package framework.global

import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest

class Call {

    var system: String = ""
    var module: String = ""
    var work: String = ""
    var deep: Int = 0

    fun globalInit(req: HttpServletRequest)
    {
        val str = ""
        val pt = Pattern.compile("/",Pattern.CASE_INSENSITIVE)
        val ar = pt.split(req.contextPath)
        this.deep = ar.size
    }
}