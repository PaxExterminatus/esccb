package framework.global.settings

import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.JsonPath

data class Email(val json: String) {

    val login: String
    val password: String
    val smtpHost: String
    val smtpPort: Int
    val smptAuth: Boolean
    val smptStarttlsEnable: Boolean
    val imapHost: String
    val imapPort: Int
    val imapFolber: String
    val messageFromName: String
    val messageFromAddress: String

    val testRecipient: String
    val testTitle: String
    val testBody: String

    init {
        val jsonObj = Configuration.defaultConfiguration().jsonProvider().parse(json)

        login = JsonPath.read(jsonObj, "$.login")
        password = JsonPath.read(jsonObj, "$.password")

        smtpHost = JsonPath.read(jsonObj, "$.smtp.host")
        smtpPort = JsonPath.read(jsonObj, "$.smtp.port")
        smptAuth = JsonPath.read(jsonObj, "$.smtp.auth")
        smptStarttlsEnable = JsonPath.read(jsonObj, "$.smtp.starttls.enable")

        imapHost = JsonPath.read(jsonObj, "$.imap.host")
        imapPort = JsonPath.read(jsonObj, "$.imap.port")
        imapFolber = JsonPath.read(jsonObj, "$.imap.folber")

        messageFromName =  JsonPath.read(jsonObj, "$.message.from.name")
        messageFromAddress = JsonPath.read(jsonObj, "$.message.from.address")

        testRecipient = JsonPath.read(jsonObj, "$.test.recipient")
        testTitle = JsonPath.read(jsonObj, "$.test.title")
        testBody = JsonPath.read(jsonObj, "$.test.body")
    }
}