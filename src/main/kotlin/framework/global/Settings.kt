package framework.global

import java.io.File
import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.JsonPath
import framework.document
import java.sql.Connection
import java.sql.DriverManager

class Settings {
    val separator = System.getProperty("file.separator")!!
    lateinit var application: String
    lateinit var settings: String
    lateinit var view: String

    lateinit var app: String
    //lateinit var url: String

    lateinit var db: String
    lateinit var email: String

    fun load(ways: String)
    {
        application = ways
        settings = ways + "settings" + separator
        view = ways + "view" + separator

        app = File(settings + "app.json").inputStream().bufferedReader().use { it.readText() }
        db = File(settings + "db.json").inputStream().bufferedReader().use { it.readText() }
        email = File(settings + "email.json").inputStream().bufferedReader().use { it.readText() }

        //
        //url = JsonPath.read(JApp,"$.urlHost")
    }

    fun dbConnection(dbName: String): Connection
    {
        val dbJson = Configuration.defaultConfiguration().jsonProvider().parse(db)

        val driver: String = JsonPath.read(dbJson, "$.$dbName.driver")
        val url: String = JsonPath.read(dbJson, "$.$dbName.url")
        val user: String = JsonPath.read(dbJson, "$.$dbName.user")
        val password: String = JsonPath.read(dbJson, "$.$dbName.password")

        Class.forName(driver);
        return DriverManager.getConnection(url, user, password)
    }
}