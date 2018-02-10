package application.implementation.sas.module

import application.implementation.sas.api.Data
import application.implementation.sas.api.Rest
import framework.call
import framework.document
import framework.module.FModule

class Sas : FModule() {
    override val moduleName: String = "sas"
    override val workNames: Array<String> = arrayOf("index","data","api")

    override fun workRouter(work: String, queryString: String) {
        when (call.work) {
            "index" -> indexWork()
            "data" -> Data().dataGet(call.params)
            "api" -> Rest()
            else -> exception("WNS")
        }
    }

    private fun indexWork() {
        document.add("sas/index")
    }
}