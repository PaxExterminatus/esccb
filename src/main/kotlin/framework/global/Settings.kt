package framework.global

import java.io.File
import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.JsonPath

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

        //val JApp = Configuration.defaultConfiguration().jsonProvider().parse(app)
        //url = JsonPath.read(JApp,"$.urlHost")
    }
}