package framework.global

import framework.document
import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest

class Call {

    var application: String = ""
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
        deep = callWays.size

        if (deep > 0)
        {
            application = callWays[0]
            system = "index"
        }
        if (deep > 1)
        {
            system = callWays[1]
            module = "index"
        }
        if (deep > 2)
        {
            module = callWays[2]
            work = "index"
        }
        if (deep > 3)
        {
            work = callWays[3]
        }

        document.addDebug("application: $application")
        document.addDebug("system: $system")
        document.addDebug("module: $module")
        document.addDebug("work: $work")
    }

    fun resetCall(){
        application = ""
        system = ""
        module = ""
        work = ""
    }
}