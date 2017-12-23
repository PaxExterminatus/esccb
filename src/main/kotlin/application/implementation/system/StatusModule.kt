package application.implementation.system

import framework.call
import framework.document
import framework.system.SysModule
import framework.ways
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

        document.add("<h2>Application Ways</h2>")
        document.add("Separator: ${ways.separator}")
        document.add("Application: ${ways.application}")
        document.add("Settings: ${ways.settings}")
        document.add("View: ${ways.view}")
    }
}