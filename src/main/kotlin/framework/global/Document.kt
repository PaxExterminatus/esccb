package framework.global

import framework.settings

class Document
{
    var context = ""
    var debug = ""

    private var templateTop = "" +
            "<!DOCTYPE html>" +
            "<html lang=\"ru-RU\">" +
            "<head>" +
            "<meta charset=\"UTF-8\">" +
            "</head>" +
            "<body>"
    private var templateBottom = "" +
            "</body>" +
            "</html>"

    fun content(): String{
        return "$templateTop${contextDebugShow()}$context$templateBottom"
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

    fun contextClear(){
        context = ""
    }

    fun debugClear(){
        debug = ""
    }
}