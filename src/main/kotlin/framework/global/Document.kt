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
        clear()
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

    private fun clear() {
        context = ""
        debug = ""
    }
}