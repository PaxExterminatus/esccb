package framework.global

import java.io.File
import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.JsonPath
import java.sql.Connection
import java.sql.DriverManager

class Settings {
    val separator = System.getProperty("file.separator")!!
    lateinit var application: String
    lateinit var settings: String
    lateinit var view: String

    //Содержание файлов конфигурации
    lateinit var app: String
    lateinit var db: String
    lateinit var email: String

    var debugUse: Boolean = false

    fun load(ways: String)
    {
        application = ways
        settings = ways + "settings" + separator
        view = ways + "view" + separator

        app = File(settings + "app.json").inputStream().bufferedReader().use { it.readText() }
        db = File(settings + "db.json").inputStream().bufferedReader().use { it.readText() }
        email = File(settings + "email.json").inputStream().bufferedReader().use { it.readText() }

        val jsonApp = Configuration.defaultConfiguration().jsonProvider().parse(app)
        debugUse = JsonPath.read(jsonApp, "$.debugUse")
    }

    fun dbConnection(dbName: String): Connection
    {
        val json = Configuration.defaultConfiguration().jsonProvider().parse(db)

        val driver: String = JsonPath.read(json, "$.$dbName.driver")
        val url: String = JsonPath.read(json, "$.$dbName.url")
        val user: String = JsonPath.read(json, "$.$dbName.user")
        val password: String = JsonPath.read(json, "$.$dbName.password")

        Class.forName(driver)
        return DriverManager.getConnection(url, user, password)
    }
}