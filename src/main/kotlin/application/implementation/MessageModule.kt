package application.implementation

import framework.call
import framework.document
import framework.system.SysModule

class MessageModule : SysModule()
{
    override val moduleName: String = "message"
    override val workNames: Array<String> = arrayOf("send","preview")

    override fun workRouter(work: String, queryString: String) {
        if (workNames.contains(call.work)){
            if (call.work == "preview") previewWork()
            if (call.work == "send") sendWork()
        } else {
            exception("WNS")
        }
    }

    private fun previewWork()
    {
        document.add("previewWork")
    }

    private fun sendWork()
    {
        document.add("sendWork")
    }
}