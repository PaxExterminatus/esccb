package application.module.sas

import application.module.sas.api.Data
import application.module.sas.api.Rest
import framework.call
import framework.module.FWException
import framework.module.IModule

class Sas : IModule {
    override val moduleName: String = "sas"
    override val workNames: Array<String> = arrayOf("index","data","api")

    override fun workRouter(work: String, queryString: String) {
        //if (work !in workNames) throw Exception("Work Not Supported")


        when (work) {
            Works.Index.work -> indexWork()
            Works.Data.work -> Data().dataGet(call.params)
            Works.Rest.work -> Rest()
            else -> exception(FWException.ModuleNotSupported())
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