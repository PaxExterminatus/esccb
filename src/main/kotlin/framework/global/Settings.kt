package framework.global

import java.io.File
import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.JsonPath
import framework.global.settings.*

class Settings {
    val separator = System.getProperty("file.separator")!!
    lateinit var pathApp: String
    lateinit var pathSettings: String
    lateinit var pathView: String

    //Содержание файлов конфигурации
    lateinit var app: String
    lateinit var db: String
    lateinit var emailJson: String
    lateinit var webJson: String

    //Откладка
    var debugUse: Boolean = false
    //Базы данных
    val sqlInsertBatchSize = 2500
    //Email
    lateinit var email: Email
    lateinit var web: Web

    fun fill(appRootPath: String)
    {
        pathApp = appRootPath
        pathSettings = appRootPath + "settings" + separator
        pathView = appRootPath + "view" + separator

        app = File(pathSettings + "app.json").inputStream().bufferedReader().use { it.readText() }
        db = File(pathSettings + "db.json").inputStream().bufferedReader().use { it.readText() }

        emailJson = File(pathSettings + "email.json").inputStream().bufferedReader().use { it.readText() }
        email = Email(emailJson)

        webJson = File(pathSettings + "web.json").inputStream().bufferedReader().use { it.readText() }
        web = Web(webJson)

        val jsonApp = Configuration.defaultConfiguration().jsonProvider().parse(app)
        debugUse = JsonPath.read(jsonApp, "$.debugUse")
    }

    fun dbSetting(settingName: String, dbName: String): String
    {
        val json = Configuration.defaultConfiguration().jsonProvider().parse(db)
        return JsonPath.read(json, "$.$dbName.$settingName")
    }
}