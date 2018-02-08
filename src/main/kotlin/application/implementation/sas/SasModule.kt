package application.implementation.sas

import framework.call
import framework.document
import framework.module.BaseModule

class SasModule : BaseModule() {
    override val moduleName: String = "sas"
    override val workNames: Array<String> = arrayOf("index","data","api")

    override fun workRouter(work: String, queryString: String) {
        when (call.work) {
            "index" -> indexWork()
            "data" -> SasDataModule()
            "api" -> SasApiModule()
            else -> exception("WNS")
        }
    }

    private fun indexWork() {
        document.add("sas/index")
    }
}