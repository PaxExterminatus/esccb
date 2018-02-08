package application.implementation.sas

import framework.document
import framework.module.DataModule

class SasDataModule: DataModule() {

    override fun dataGet(params: MutableMap<String, String>) {
        val entity = params["entity"]
        val by = params["by"]
        val value = params["where"]
        val view = params["view"] ?: "json" //todo default json

        document.add("entity $entity / by $by / where $value / view $view")
    }
}