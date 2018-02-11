package framework.global

import framework.settings

class Document
{
    private var context = ""
    var debug = ""

    private var templateBottom = "</body></html>"

    private fun templateTopBuild(): String {
        return "<!DOCTYPE html>" +
                "<html lang=\"${settings.web.lang}\">" +
                "<head>" +
                "<meta charset=\"${settings.web.charset}\">" +
                "</head>" +
                "<body>"
    }

    fun content(): String {
        return "${templateTopBuild()}${contextDebugShow()}$context$templateBottom"
    }

    private fun contextDebugShow(): String {
        return if (settings.debugUse) debug else ""
    }

    fun addDebug(str: String) {
        debug += str + "<br>"
    }

    fun add(str: String) {
        context += str + "<br>"
    }

    fun clear() {
        context = ""
        debug = ""
    }
    fun api(result: String, format: String = "java") {
        clear()
        when (format) {
            "java" -> this.add(result)
        }
    }
}