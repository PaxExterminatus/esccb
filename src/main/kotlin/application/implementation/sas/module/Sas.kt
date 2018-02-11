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
        //if (work !in workNames) throw Exception("Work Not Supported")

        when (work) {
            Works.Index.work -> indexWork()
            Works.Data.work -> Data().dataGet(call.params)
            Works.Rest.work -> Rest()
            else -> exception("WNS")
        }
    }

    private fun indexWork() {
        document.add("sas/index")
    }

    enum class Works(val work: String) {
        Index("index"),
        Data("data"),
        Rest("rest"),
        GraphQL("graph");
    }
}