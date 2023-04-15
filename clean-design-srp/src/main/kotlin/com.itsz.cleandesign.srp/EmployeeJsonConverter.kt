package com.itsz.cleandesign.srp

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException

class EmployeeJsonConverter {

    private val mapper = ObjectMapper()

    fun employeesAsJson(employees: List<Employee>):String {
        try {
            return mapper.writeValueAsString(employees)
        } catch (e: IOException) {
            throw IllegalStateException(e)
        }
    }
}