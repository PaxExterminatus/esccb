package framework.global

import framework.document
import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest
import java.util.Arrays

class Call {

    var system: String = ""
    var module: String = ""
    var work: String = ""
    var deep: Int = 0
    lateinit var callWays: Array<String>

    fun initCall(req: HttpServletRequest)
    {
        val pt = Pattern.compile("/",Pattern.CASE_INSENSITIVE)

        val requestURI: String = if (req.requestURI[0] == '/') req.requestURI.substring(1) else req.requestURI
        callWays = pt.split(requestURI)
        this.deep = callWays.size
    }
}