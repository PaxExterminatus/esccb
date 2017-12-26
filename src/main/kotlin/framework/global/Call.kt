package framework.global

import framework.debugContextShow
import framework.document
import framework.settings
import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest

class Call {

    private var application: String = ""
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
            module = "index"
        }
        if (deep > 1)
        {
            module = callWays[1]
            work = "index"
        }
        if (deep > 2)
        {
            work = callWays[2]
        }

        if (settings.debugUse)
        {
            document.addDebug("application: $application")
            document.addDebug("module: $module")
            document.addDebug("work: $work")
        }
    }

    fun resetCall()
    {
        application = ""
        module = ""
        work = ""
    }
}