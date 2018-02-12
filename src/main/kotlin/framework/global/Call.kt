package framework.global

import framework.*
import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest

class Call
{
    lateinit var callWays: List<String>
    var deep: Int = 1
    var module: String = ""
    var work: String = ""

    var params: MutableMap<String, String> = hashMapOf()

    fun fill(req: HttpServletRequest) {
        clear()

        //extract data
        val requestURI: String = if (req.requestURI[0] == '/') req.requestURI.substring(1) else req.requestURI
        callWays = requestURI.split("/")

        deep = callWays.size
        module = moduleGet()
        work = workGet()
        val parameterNames = req.parameterNames
        while (parameterNames.hasMoreElements()) {
            val paramName = parameterNames.nextElement()
            params[paramName] = req.getParameter(paramName)
        }

        if (settings.debugUse) {
            document.addDebug("module: $module / work: $work ? params: $params")
        }
    }

    private fun moduleGet(): String = if (deep >= 2) callWays[1] else "index"
    private fun workGet(): String = if (deep >= 3) callWays[2] else "index"

    private fun clear() {
        module = ""
        work = ""
        params.clear()
    }
}