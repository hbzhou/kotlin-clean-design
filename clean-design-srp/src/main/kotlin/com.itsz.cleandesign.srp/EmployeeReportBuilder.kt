package com.itsz.cleandesign.srp

object EmployeeReportBuilder {
    fun build(employees: List<Employee>):String{
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
}