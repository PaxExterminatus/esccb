package application.module.communication

import framework.call
import framework.document
import framework.exception.FWEWorkNotSupported
import framework.module.IModule

class Communication: IModule
{
    override val moduleName: String = "stream"
    override val workNames: Array<String> = arrayOf("preview", "send")

    override fun workRouter(work: String, queryString: String) {
        when (work) {
            "preview" -> previewWork("","")
            "send" -> sendWork(call.params["cause"]!!)
            else -> throw FWEWorkNotSupported()
        }
    }

    private fun previewWork(cause: String, user: String) {
        view("hello.html")
    }

    private fun sendWork(cause: String) {
        document.add("sendWork, cause = $cause")
    }
}