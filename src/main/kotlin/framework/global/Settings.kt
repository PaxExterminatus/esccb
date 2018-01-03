package framework.global

import java.io.File
import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.JsonPath
import framework.global.settings.Email

class Settings {
    val separator = System.getProperty("file.separator")!!
    lateinit var application: String
    lateinit var settings: String
    lateinit var view: String

    //Содержание файлов конфигурации
    lateinit var app: String
    lateinit var db: String
    lateinit var emailJson: String
    lateinit var web: String

    //Откладка
    var debugUse: Boolean = false
    //Базы данных
    val sqlInsertBatchSize = 2500
    //Веб
    lateinit var webCharset: String
    lateinit var webLang: String
    //Email
    lateinit var email: Email

    fun load(appRootPath: String)
    {
        application = appRootPath
        settings = appRootPath + "settings" + separator
        view = appRootPath + "view" + separator

        app = File(settings + "app.json").inputStream().bufferedReader().use { it.readText() }
        db = File(settings + "db.json").inputStream().bufferedReader().use { it.readText() }

        emailJson = File(settings + "email.json").inputStream().bufferedReader().use { it.readText() }
        email = Email(emailJson)

        web = File(settings + "web.json").inputStream().bufferedReader().use { it.readText() }

        webCharset = webSetting("charset")
        webLang = webSetting("lang")

        val jsonApp = Configuration.defaultConfiguration().jsonProvider().parse(app)
        debugUse = JsonPath.read(jsonApp, "$.debugUse")
    }

    fun dbSetting(settingName: String, dbName: String): String
    {
        val json = Configuration.defaultConfiguration().jsonProvider().parse(db)
        return JsonPath.read(json, "$.$dbName.$settingName")
    }

    private fun webSetting(settingName: String): String {
        val json = Configuration.defaultConfiguration().jsonProvider().parse(web)
        return JsonPath.read(json, "$.$settingName")
    }
}