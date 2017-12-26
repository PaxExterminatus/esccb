package application.implementation.system

import framework.call
import framework.document
import framework.system.SysModule
import framework.settings
import java.util.*

class StatusModule: SysModule()
{
    override val systemName: String = "system"
    override val moduleName: String = "status"
    override val actionNames: Array<String> = arrayOf("show")

    override fun workRouter(work: String, queryString: String) {
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
    }
}