package com.itsz.cleandesign.srp

import java.sql.Connection
import java.sql.SQLException

class EmployeeRepositoryImpl(private val connection: Connection) : EmployeeRepository {

    override fun findAll(): List<Employee> {
        val employeeList = mutableListOf<Employee>()
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
                        employeeList.add(employee)
                    }
                }
            }
        } catch (e: SQLException) {
            throw IllegalStateException(e)
        }
        return employeeList
    }
}