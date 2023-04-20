package com.itsz.cleandesign.srp

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException
import java.sql.Connection
import java.sql.SQLException
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EmployeeManager(connection: Connection) {

    private val emailSendService = EmailSendService()
    private val employeeRepository = EmployeeRepositoryCacheImpl(EmployeeRepositoryImpl(connection))

    fun sendEmployeesReport() {
        val to = "abcd@gamil.com"
        val from = "web@gmail.com"
        val subject = "Employees Report"
        val content = EmployeeReportBuilder.build(employeeRepository.findAll())
        emailSendService.sendEmail(EmailSendDto(from, to, subject, content))
    }

}