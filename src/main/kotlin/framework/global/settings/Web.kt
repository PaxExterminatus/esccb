package framework.global.settings

import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.JsonPath
import java.util.regex.Pattern

data class Web(val json: String) {
    val lang: String
    val charset: String

    init {
        val jsonObj = Configuration.defaultConfiguration().jsonProvider().parse(json)

        lang = JsonPath.read(jsonObj, "$.lang")
        charset = JsonPath.read(jsonObj, "$.charset")
    }

    fun langAsArray(): Array<String> {
        return Pattern.compile(",", Pattern.CASE_INSENSITIVE).split(lang)
    }
}