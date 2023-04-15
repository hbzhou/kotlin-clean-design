package com.itsz.cleandesign.srp

import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import javax.mail.Message
import javax.mail.Transport
import kotlin.test.BeforeTest
import kotlin.test.assertEquals


 class EmployeeManagerTest {

    @Mock
    private lateinit var mockConnection: Connection

    @Mock
    private lateinit var resultSetMock: ResultSet

    @BeforeTest
    @Throws(SQLException::class)
    fun init() {
        MockitoAnnotations.openMocks(this)
        val mockStatement: Statement = mock(Statement::class.java)
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(any())).thenReturn(resultSetMock)
    }

    @Test
    @Throws(Exception::class)
    fun sendMultipleEmployeesReport() {
        setUpResultSetMock(
            listOf(
                Employee("Wayne", "Rooney", EmployeeRole.SOFTWARE_ENGINEER, EmployeeSeniority.LEAD),
                Employee(
                    "Harry", "Kane", EmployeeRole.SOFTWARE_TEST_ENGINEER,
                    EmployeeSeniority.JUNIOR
                )
            )
        )
        testSendMail(
            ("<table>" + "<tr><th>Employee</th><th>Position</th></tr>" +
                    "<tr><td>Wayne Rooney</td><td>LEAD SOFTWARE_ENGINEER</td></tr>" +
                    "<tr><td>Harry Kane</td><td>JUNIOR SOFTWARE_TEST_ENGINEER</td></tr>" + "</table>")
        )
    }

    @Throws(SQLException::class)
    private fun setUpResultSetMock(employees: List<Employee>) {
        val index = AtomicInteger()
        val size = employees.size
        `when`(resultSetMock.next()).thenAnswer { index.get() < size && index.incrementAndGet() < size }
        `when`(resultSetMock.previous()).thenAnswer { index.get() >= 0 && index.decrementAndGet() >= 0 }
        `when`(resultSetMock.isBeforeFirst).thenAnswer { index.get() == -1 }
        `when`(resultSetMock.first()).thenAnswer { employees.isNotEmpty() && index.getAndSet(0) != size }
        `when`(resultSetMock.isAfterLast).thenAnswer { index.get() == size }
        `when`(resultSetMock.first()).thenAnswer { employees.isNotEmpty() && index.getAndSet(size - 1) != size }
        `when`(resultSetMock.getString("FIRST_NAME")).thenAnswer {
            employees[index.get()].firstName
        }
        `when`(resultSetMock.getString("LAST_NAME")).thenAnswer {
            employees[index.get()].lastName
        }
        `when`(resultSetMock.getString("ROLE")).thenAnswer { employees[index.get()].role.name }
        `when`(resultSetMock.getString("SENIORITY"))
            .thenAnswer { employees[index.get()].seniority.name }
        index.set(-1)
    }



    @Throws(Exception::class)
    private fun testSendMail(expected: String) {
        val propertiesCaptor = ArgumentCaptor.forClass(Message::class.java)
        mockStatic(Transport::class.java).use { mockedStatic ->
            val manager = EmployeeManager(mockConnection)
            manager.sendEmployeesReport()
            mockedStatic.verify { Transport.send(propertiesCaptor.capture()) }
            assertEquals(propertiesCaptor.value.content, expected)

            //check caching
            clearInvocations(resultSetMock)
            `when`(resultSetMock.next()).thenReturn(false)
            manager.sendEmployeesReport()
            assertEquals(propertiesCaptor.value.content, expected)

        }

    }

}