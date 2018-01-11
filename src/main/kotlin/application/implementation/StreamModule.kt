package application.implementation

import framework.call
import framework.document
import framework.system.SysModule

class StreamModule : SysModule()
{
    override val moduleName: String = "stream"
    override val workNames: Array<String> = arrayOf("preview", "send")

    override fun workRouter(work: String, queryString: String) {
        if (workNames.contains(call.work)){
            if (call.work == "preview") previewWork("")
            if (call.work == "send") sendWork()
        } else {
            exception("WNS")
        }
    }

    private fun previewWork(cause: String)
    {
        document.add("previewWork")
    }

    private fun sendWork()
    {
        document.add("sendWork")
    }
}