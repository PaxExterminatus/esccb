package application.implementation.sas.api

import framework.document
import framework.api.FData

class Data : FData() {

    override fun dataGet(params: MutableMap<String, String>) {
        val method = params["method"]
        val param = params["param"]
        val source = params["source"]
        val data = params["data"]

        val view = params["view"] ?: "json"


        val result = listOf(
                hashMapOf("name" to "vasya"),
                hashMapOf("cource" to "eng"),
                hashMapOf("lesson" to "12333")
        )

        document.api(result.toString())

        document.add("method $method / by $param / deep $source / view $view / data $data")
        //http://localhost:8181/esccb/sas/data?method=findById&param=10&source=client.cources.counteiners&data=cources[name,unit,status]counteiners[lesson,pay,cost]
    }
}