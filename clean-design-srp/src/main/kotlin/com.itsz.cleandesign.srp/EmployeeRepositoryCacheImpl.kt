package com.itsz.cleandesign.srp

class EmployeeRepositoryCacheImpl(private val delegator: EmployeeRepository) : EmployeeRepository {

    private var cache: List<Employee>? = null

    override fun findAll(): List<Employee> {
        if (cache == null) {
            cache = delegator.findAll()
        }
        return cache!!
    }
}