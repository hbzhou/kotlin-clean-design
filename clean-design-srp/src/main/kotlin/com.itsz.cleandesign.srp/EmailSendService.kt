package com.itsz.cleandesign.srp

import java.util.Properties
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EmailSendService {

    fun sendEmail(emailSendDto: EmailSendDto) {
        val properties = System.getProperties()
        properties.setProperty("mail.smtp.host","localhost")
        val message = MimeMessage(Session.getDefaultInstance(properties))
        try {
            message.subject = emailSendDto.subject
            message.setFrom(InternetAddress(emailSendDto.from))
            message.addRecipient(Message.RecipientType.TO, InternetAddress(emailSendDto.to))
            message.setContent(emailSendDto.content, "text/html; charset=utf-8")

            Transport.send(message)
        } catch (e: MessagingException) {
            throw IllegalStateException(e)
        }
    }
}