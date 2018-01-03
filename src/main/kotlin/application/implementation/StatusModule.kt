package application.implementation

import application.source.DatabaseCross
import application.source.DatabaseSas
import framework.call
import framework.document
import framework.system.SysModule
import framework.settings
import oracle.jdbc.driver.DatabaseError
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*

class StatusModule: SysModule()
{
    override val moduleName: String = "status"
    override val workNames: Array<String> = arrayOf("show")

    override fun workRouter(work: String, queryString: String)
    {
        if (workNames.contains(call.work)){
            if (call.work == "show") showWork()
        } else {
            exception("WNS")
        }
    }

    private fun showWork()
    {
        document.add("<h1>Status Page</h1>")

        document.add("<h2>Call</h2>")
        document.add("Ways: ${Arrays.toString(call.callWays)}")
        document.add("Deep: ${call.deep}")

        document.add("<h2>Application Settings</h2>")
        document.add("Separator: ${settings.separator}")
        document.add("Application: ${settings.application}")
        document.add("Settings: ${settings.settings}")
        document.add("View: ${settings.view}")

        document.add("<h2>Configuration Files</h2>")
        document.add("Application:<br> ${settings.app}")
        document.add("Database:<br> ${settings.db}")
        document.add("Email:<br> ${settings.email}")
        document.add("Web:<br> ${settings.web}")

        document.add("<h2>Database</h2>")

        document.add("<h3>CROSS database version</h3>")
        try {
            val crossDb = DatabaseCross()
            val resultSet = crossDb.selectResultSet("dual","VERSION()")
            while (resultSet.next())
                document.add(resultSet.getString(1))
            resultSet.close()
        }
        catch (ex: Exception)
        {
            val sw = StringWriter()
            ex.printStackTrace(PrintWriter(sw))
            document.add(sw.toString())
        }

        document.add("<h3>SAS database version</h3>")
        try {
            val sasDb = DatabaseSas()
            val resultSet = sasDb.selectResultSet("V${'$'}VERSION")
            while (resultSet.next())
                document.add(resultSet.getString(1))
            resultSet.close()
        }
        catch (ex: Exception)
        {
            val sw = StringWriter()
            ex.printStackTrace(PrintWriter(sw))
            document.add(sw.toString())
        }
    }
}