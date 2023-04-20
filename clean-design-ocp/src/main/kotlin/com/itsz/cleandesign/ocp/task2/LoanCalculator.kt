package com.itsz.cleandesign.ocp.task2

interface LoanCalculator {

    fun calculate(age: Int, income: Int): Int

//    fun getStudentLoan(age: Int, income: Int): Int {
//        var loan = 100
//        if (age >= 21) {
//            loan += 150
//        }
//        return IncomeMultiplier.multiply(loan, income)
//    }
//
//    fun getCarLoan(age: Int, income: Int): Int {
//        var loan = 2000
//        if (age > 50) {
//            loan += 1500
//        } else if (age > 30) {
//            loan += 1000
//        }
//        return IncomeMultiplier.multiply(loan, income)
//    }
//
//    fun getHouseLoan(age: Int, income: Int): Int {
//        var loan = 100000
//        if (age > 30 && income > loan / 2) {
//            loan *= 2
//        }
//        return IncomeMultiplier.multiply(loan, income)
//    }


}