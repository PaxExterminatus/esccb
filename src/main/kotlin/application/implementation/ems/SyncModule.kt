package application.implementation.ems

import framework.call
import framework.document
import framework.system.SysModule

class SyncModule: SysModule()
{
    override val moduleName: String = "sync"
    override val workNames: Array<String> = arrayOf("copy","log")

    override fun workRouter(work: String, queryString: String)
    {
        if (workNames.contains(call.work)){
            if (call.work == "copy") copyWork()
            if (call.work == "log") logWork()
        } else {
            exception("WNS")
        }
    }

    private fun copyWork() {
        document.add("<h1>copyWork</h1>")
    }

    private fun logWork() {
        document.add("<h1>logWork</h1>")
    }
}