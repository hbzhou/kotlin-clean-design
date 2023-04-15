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

class EmployeeManager {

    private  var cache = mutableListOf<Employee>()

    fun sendEmployeesReport(connection: Connection) {
        val to = "abcd@gamil.com"
        val from = "web@gmail.com"
        val host = "localhost"

        val properties = System.getProperties()
        properties.setProperty("mail.smtp.host", host)
        val session = Session.getDefaultInstance(properties)

        val message = MimeMessage(session)
        try {
            message.subject = "Employees report"
            message.setFrom(InternetAddress(from))
            message.addRecipient(Message.RecipientType.TO, InternetAddress(to))

            val employeesHtml = getAllEmployeesAsHtml(connection)
            message.setContent(employeesHtml, "text/html; charset=utf-8")

            Transport.send(message)
        } catch (e: MessagingException) {
            throw IllegalStateException(e)
        }
    }

    @Synchronized
    private fun getAllEmployeesAsHtml(connection: Connection): String {
        val employees: List<Employee> = readEmployees(connection)
        val builder = StringBuilder()
        builder.append("<table>").append("<tr><th>Employee</th><th>Position</th></tr>")
        for (employee in employees) {
            builder.append("<tr><td>").append(employee.firstName).append(" ").append(employee.lastName)
                .append("</td><td>").append(employee.seniority).append(" ").append(employee.role)
                .append("</td></tr>")
        }
        builder.append("</table>")
        return builder.toString()
    }

    @Synchronized
    fun employeesAsJson(connection: Connection): String {
        val employees = readEmployees(connection)
        val mapper = ObjectMapper()
         try {
           return mapper.writeValueAsString(employees)
        } catch (e: IOException) {
            throw IllegalStateException(e)
        }
    }


    private fun readEmployees(connection: Connection): List<Employee> {
        if (cache.isEmpty()) {
            try {
                connection.createStatement().use { statement ->
                    statement.executeQuery("SELECT * FROM Employees").use { resultSet ->
                        while (resultSet.next()) {
                            val employee = Employee(
                                firstName = resultSet.getString("FIRST_NAME"),
                                lastName = resultSet.getString("LAST_NAME"),
                                role = EmployeeRole.valueOf(resultSet.getString("ROLE")),
                                seniority = EmployeeSeniority.valueOf(resultSet.getString("SENIORITY"))
                            )
                           cache.add(employee)
                        }
                    }
                }
            } catch (e: SQLException) {
                throw IllegalStateException(e)
            }
        }
        return cache
    }
}