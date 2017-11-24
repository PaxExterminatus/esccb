package framework.global

class Document
{
    var context = "Hi, mr. <strong>Hitler</strong>"

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
        return "$templateTop$context$templateBottom"
    }

}