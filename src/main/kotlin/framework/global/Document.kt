package framework.global

import framework.settings

class Document
{
    var context = ""
    var contextDebug = ""

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

    fun contextDebugShow(): String {
        if (settings.debugUse)
            return contextDebug
        else
            return ""
    }

    fun addDebug(str: String) {
        contextDebug += str + "<br>"
    }

    fun add(str: String) {
        context += str + "<br>"
    }

}