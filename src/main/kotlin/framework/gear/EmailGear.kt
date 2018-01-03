package framework.gear

import framework.settings
import java.util.*
import javax.mail.*
import javax.mail.internet.*

class EmailGear {

    fun send(recipient :String, title: String, body: String) {
        val properties = Properties()
        properties.put("mail.smtp.host", settings.email.smtpHost)
        properties.put("mail.smtp.port", settings.email.smtpPort)
        properties.put("mail.smtp.auth", settings.email.smptAuth)
        properties.put("mail.smtp.starttls.enable", settings.email.smptStarttlsEnable)

        val smtpAuth: Authenticator = SMTPAuthenticator()
        val session: Session = Session.getInstance(properties, smtpAuth)

        val message = MimeMessage(session)
        message.contentLanguage = settings.web.langAsArray()
        message.setFrom(InternetAddress(settings.email.messageFromAddress, settings.email.messageFromName))
        message.setRecipients(Message.RecipientType.TO, recipient)
        message.sentDate = Date()
        message.subject = title
        message.setText(body, settings.web.charset, "html")

        Transport.send(message)

        val store: Store = session.getStore("imap")
        store.connect(
                settings.email.imapHost,
                settings.email.imapPort,
                settings.email.login,
                settings.email.password)
        val folder = store.getFolder(settings.email.imapFolber)

        if (!folder.exists()) {
            folder.create(Folder.HOLDS_MESSAGES)
        }
        folder.open(Folder.READ_WRITE)
        val messages= Array(1) {message}
        folder.appendMessages(messages)
        store.close()
    }

    private class SMTPAuthenticator: Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication("vitaliy.voronin@eshko.by","C9Ask2YalzpDzvkTAarAMirA0k54MXw")
        }
    }

}