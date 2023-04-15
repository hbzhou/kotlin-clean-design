package com.itsz.cleandesign.srp

interface EmployeeRepository {

    fun findAll():List<Employee>
}