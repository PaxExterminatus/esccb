package application.module.ems

import application.source.DatabaseCross
import framework.call
import framework.data.Database
import framework.document
import framework.module.FWException
import framework.module.IModule
import java.io.PrintWriter
import java.io.StringWriter

class EmsData: IModule
{
    override val moduleName: String = "sync"
    override val workNames: Array<String> = arrayOf("sync","log")

    override fun workRouter(work: String, queryString: String)
    {
        if (workNames.contains(call.work)){
            if (call.work == "sync") syncWork()
            if (call.work == "log") logWork()
        } else {
            exception(FWException.ModuleNotSupported())
        }
    }

    private fun syncWork()
    {
        document.add("<h1>Email Marketing System</h1>")
        try
        {
            val sasDb = Database("sas")
            val crossDb = DatabaseCross()
            val rs = sasDb.selectResultSet("EMS_CLIENT_SYNC")
            crossDb.truncateEmsClientTable()
            crossDb.insert("EMS_CLIENT",rs)
            document.add("Sync Complite")
        }
        catch (ex: Exception)
        {
            document.add("Sync Error")
            val sw = StringWriter()
            ex.printStackTrace(PrintWriter(sw))
            document.add(sw.toString())
        }
    }

    private fun logWork() {
        document.add("<h1>logWork</h1>")
    }
}