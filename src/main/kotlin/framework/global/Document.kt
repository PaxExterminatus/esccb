package framework.global

import framework.settings

class Document
{
    private var context = ""
    private var debug = ""

    private var templateBottom = "</body></html>"

    private fun templateTopBuild(): String {
        return "<!DOCTYPE html>" +
                "<html lang=\"${settings.webLang}\">" +
                "<head>" +
                "<meta charset=\"${settings.webCharset}\">" +
                "</head>" +
                "<body>"
    }

    fun content(): String {
        return "${templateTopBuild()}${contextDebugShow()}$context$templateBottom"
    }

    private fun contextDebugShow(): String {
        if (settings.debugUse)
            return debug
        else
            return ""
    }

    fun addDebug(str: String)
    {
        debug += str + "<br>"
    }

    fun add(str: String)
    {
        context += str + "<br>"
    }

    fun clear() {
        contextClear()
        debugClear()
    }

    private fun contextClear(){
        context = ""
    }

    private fun debugClear(){
        debug = ""
    }
}