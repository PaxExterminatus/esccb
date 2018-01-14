package framework.global

import framework.*
import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest

class Call
{
    lateinit var callWays: Array<String>
    var deep: Int = 1
    var application: String = ""
    var module: String = ""
    var work: String = ""

    fun initCall(req: HttpServletRequest) {
        val separatorPattern = Pattern.compile("/",Pattern.CASE_INSENSITIVE)
        val requestURI: String = if (req.requestURI[0] == '/') req.requestURI.substring(1) else req.requestURI
        callWays = separatorPattern.split(requestURI)
        deep = callWays.size

        application = callWays[0]

        module = moduleGet()
        work = workGet()

        if (settings.debugUse) {
            document.addDebug("application: $application")
            document.addDebug("module: $module")
            document.addDebug("work: $work")
        }
    }

    private fun moduleGet(): String {
        return if (deep >= 1) callWays[1] else "index"
    }

    private fun workGet(): String {
        return if (deep >= 2) callWays[2] else "index"
    }

    fun clear() {
        application = ""
        module = ""
        work = ""
    }
}