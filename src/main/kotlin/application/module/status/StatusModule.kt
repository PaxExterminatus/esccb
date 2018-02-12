package application.module.status

import application.source.DatabaseCross
import application.source.DatabaseSas
import framework.call
import framework.document
import framework.gear.EmailGear
import framework.module.IModule
import framework.settings
import java.io.PrintWriter
import java.io.StringWriter

class StatusModule: IModule
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
        document.add("<h1>Status Page V2</h1>")

        document.add("<h2>Call</h2>")
        document.add("Ways: ${call.callWays}")
        document.add("Deep: ${call.deep}")

        document.add("<h2>Application Settings</h2>")
        document.add("Separator: ${settings.separator}")
        document.add("Application: ${settings.pathApp}")
        document.add("Settings: ${settings.pathSettings}")
        document.add("View: ${settings.pathView}")

        document.add("<h2>Configuration Files</h2>")
        document.add("Application:<br> ${settings.app}")
        document.add("Database:<br> ${settings.db}")
        document.add("Email:<br> ${settings.emailJson}")
        document.add("Web:<br> ${settings.webJson}")

        document.add("<h2>Setting Classes</h2>")
        document.add("<h3>framework.global.pathSettings.Email</h3>")
        document.add("${settings.email}")
        document.add("<h3>framework.global.pathSettings.Web</h3>")
        document.add("${settings.web}")

        document.add("<h2>Email Sender</h2>")
        try {
            EmailGear().send(settings.email.testRecipient, settings.email.testTitle, settings.email.testBody)
            document.add("Success!!!")
        } catch (ex: Exception) {
            val sw = StringWriter()
            ex.printStackTrace(PrintWriter(sw))
            document.add(sw.toString())
        }

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