package application.implementation

import framework.call
import framework.document
import framework.module.FModule

class StreamModule : FModule()
{
    override val moduleName: String = "stream"
    override val workNames: Array<String> = arrayOf("preview", "send")

    override fun workRouter(work: String, queryString: String) {
        if (workNames.contains(call.work)){
            if (call.work == "preview")
            {
                previewWork("","")
            }
            if (call.work == "send")
            {
                sendWork(call.params["cause"]!!)
            }
        } else {
            exception("WNS")
        }
    }

    private fun previewWork(cause: String, user: String) {
        document.add("sendWork, cause = $cause")
    }

    private fun sendWork(cause: String) {
        document.add("sendWork, cause = $cause")
    }
}