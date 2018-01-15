package application.implementation

import framework.call
import framework.document
import framework.system.SysModule
import kotlin.reflect.KFunction
import kotlin.reflect.full.functions

//import kotlin.reflect.KFunction

class StreamModule : SysModule()
{
    override val moduleName: String = "stream"
    override val workNames: Array<String> = arrayOf("preview", "send")

    override fun workRouter(work: String, queryString: String) {
        if (workNames.contains(call.work)){
            if (call.work == "preview")
            {
                workSpote()
                previewWork(call.params["cause"]!!)
                previewWork(call.params["cause"]!!,call.params["user"]!!)
                //todo auto params insert (reflection, prototype)
                //val params = ::previewWork.parameters
                //params.forEach { i -> document.addDebug("param = ${i.name}") }
                //previewWork(call.params[::previewWork.parameters[0].name!!]!!,call.params[::previewWork.parameters[1].name!!]!!)
                //required parameter not found
            }
            if (call.work == "send")
            {
                sendWork(call.params["cause"]!!)
            }
        } else {
            exception("WNS")
        }
    }

    private fun previewWork(cause: String, user: String) =
            document.add("previewWork2, cause = $cause; user = $user")

    private fun previewWork(cause: String) =
            document.add("previewWork1, cause = $cause")


    private fun sendWork(cause: String)
    {
        document.add("sendWork, cause = $cause")
    }

    private fun workSpote() {
        document.add("v2")
        document.add("<hr>")
        val funsAll = this::class.functions

        val workRules = Regex("^.+Work\$")

        funsAll.forEach {
            fn -> if ( workRules.matches(fn.name) )
            {
                document.add(fn.name)
                val prs = fn.parameters
                prs.forEach { pr ->
                    if (pr.name != null) {
                        document.add("name = ${pr.name}; type = ${pr.type}")
                    }
                }
            }
        }

        document.add("<hr>")
    }
}