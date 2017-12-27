package application.implementation.ems

import framework.call
import framework.datasources.DataBase
import framework.document
import framework.settings
import framework.system.SysModule

class SyncModule: SysModule()
{
    override val moduleName: String = "sync"
    override val workNames: Array<String> = arrayOf("copy","log")

    override fun workRouter(work: String, queryString: String)
    {
        if (workNames.contains(call.work)){
            if (call.work == "copy") copyWork()
            if (call.work == "log") logWork()
        } else {
            exception("WNS")
        }
    }

    private fun copyWork() {
        val crossDb = DataBase(settings.dbConnection("cross"))
        val sasDb = DataBase(settings.dbConnection("sas"))
    }

    private fun logWork() {
        document.add("<h1>logWork</h1>")
    }
}