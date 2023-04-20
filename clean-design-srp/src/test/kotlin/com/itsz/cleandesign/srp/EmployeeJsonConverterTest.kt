package com.itsz.cleandesign.srp

import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert

class EmployeeJsonConverterTest {

    private val converter = EmployeeJsonConverter()

    @Test
    @Throws(Exception::class)
    fun emptyJson() {
        val serialized = converter.employeesAsJson(emptyList())
        testJsonConvert(serialized, "[]")
    }

    @Test
    @Throws(Exception::class)
    fun singleEmployeeJson() {
        val employees = listOf(
            Employee("Wayne", "Rooney", EmployeeRole.PROJECT_MANAGER, EmployeeSeniority.REGULAR)
        )
        val serialized = converter.employeesAsJson(employees)
        testJsonConvert(
            serialized,
            "[{\"firstName\":\"Wayne\",\"lastName\":\"Rooney\",\"role\":\"PROJECT_MANAGER\",\"seniority\":\"REGULAR\"}]"
        )
    }

    @Test
    @Throws(Exception::class)
    fun multipleEmployeesJson() {
        val employees = listOf(
            Employee("Wayne", "Rooney", EmployeeRole.SOFTWARE_ENGINEER, EmployeeSeniority.CHIEF),
            Employee("Harry", "Kane", EmployeeRole.SOFTWARE_TEST_AUTOMATION_ENGINEER, EmployeeSeniority.JUNIOR)
        )

        val serialized = converter.employeesAsJson(employees)
        testJsonConvert(
            serialized,
            "[{\"firstName\":\"Wayne\",\"lastName\":\"Rooney\",\"role\":\"SOFTWARE_ENGINEER\",\"seniority\":\"CHIEF\"}," +
                    "{\"firstName\":\"Harry\",\"lastName\":\"Kane\",\"role\":\"SOFTWARE_TEST_AUTOMATION_ENGINEER\",\"seniority\":\"JUNIOR\"}]"
        )
    }

    private fun testJsonConvert(serialized: String, expect: String) {
        JSONAssert.assertEquals(serialized, serialized, expect, false)
    }
}