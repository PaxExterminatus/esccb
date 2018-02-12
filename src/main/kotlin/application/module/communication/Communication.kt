package application.module.communication

import framework.call
import framework.document
import framework.module.FWException
import framework.module.IModule

class Communication: IModule
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
            exception(FWException.WorkNotSupported())
        }
    }

    private fun previewWork(cause: String, user: String) {
        view("hello.html")
    }

    private fun sendWork(cause: String) {
        document.add("sendWork, cause = $cause")
    }
}