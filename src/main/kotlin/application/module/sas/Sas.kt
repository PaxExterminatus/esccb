package application.module.sas

import application.module.sas.api.Data
import application.module.sas.api.Rest
import framework.call
import framework.exception.FWEWorkNotSupported
import framework.module.IModule

class Sas : IModule {
    override val moduleName: String = "sas"
    override val workNames: Array<String> = arrayOf("index","data","api")

    override fun workRouter(work: String, queryString: String) {
        when (work) {
            Works.Index.work -> indexWork()
            Works.Data.work -> Data().dataGet(call.params)
            Works.Rest.work -> Rest()
            else -> throw FWEWorkNotSupported()
        }
    }

    private fun indexWork() {

    }

    enum class Works(val work: String/*, val fn: KFunction<*>*/ ) {
        Index("index"),
        Data("data"),
        Rest("rest"),
        GraphQL("graph");
    }


}