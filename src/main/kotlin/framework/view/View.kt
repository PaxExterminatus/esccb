package framework.view

import framework.document
import framework.settings
import java.io.File

/**
 * Загружать файлы
 * вставлять часть html в нужные места
 */
class View(viewName: String) {

    private val viewSource: String

    init {
        val viewCurrentPath = settings.pathView + viewName
        viewSource = File(viewCurrentPath).inputStream().bufferedReader().use { it.readText() }
        document.add(viewSource)
    }
}