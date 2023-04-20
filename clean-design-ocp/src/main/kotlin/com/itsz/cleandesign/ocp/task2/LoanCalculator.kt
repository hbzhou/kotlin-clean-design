package com.itsz.cleandesign.ocp.task2

interface LoanCalculator {
    fun calculate(age: Int, income: Int): Int
}