package application.implementation.ems

import framework.call
import framework.datasources.DataBase
import framework.document
import framework.settings
import framework.system.SysModule

class EmsModule : SysModule()
{
    override val moduleName: String = "sync"
    override val workNames: Array<String> = arrayOf("sync","log")

    override fun workRouter(work: String, queryString: String)
    {
        if (workNames.contains(call.work)){
            if (call.work == "sync") syncWork()
            if (call.work == "log") logWork()
        } else {
            exception("WNS")
        }
    }

    private fun syncWork()
    {
        document.add("<h1>Email Marketing System</h1>")
        try
        {
            val sasDb = DataBase(settings.dbConnection("sas"))
            val crossDb = DataBase(settings.dbConnection("cross"))
            val rs = sasDb.selectResultSet("EMS_CLIENT_SYNC")
            crossDb.insert("EMS_CLIENT_SYNC",rs)
        }
        catch (ex: Exception)
        {
            document.add("Sync Error")
        }
        document.add("Sync Complite")
    }

    private fun logWork() {
        document.add("<h1>logWork</h1>")
    }
}