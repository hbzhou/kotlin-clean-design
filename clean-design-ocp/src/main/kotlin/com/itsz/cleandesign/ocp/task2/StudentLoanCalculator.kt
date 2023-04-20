package com.itsz.cleandesign.ocp.task2


class StudentLoanCalculator : LoanCalculator {

    companion object {
        const val BASE_LOAN = 100
        const val YOUNG_AGE = 21
        const val ADULT_EXTRA_LOAN = 150
    }

    override fun calculate(age: Int, income: Int): Int {
        val loan = if (age < YOUNG_AGE) BASE_LOAN else BASE_LOAN + ADULT_EXTRA_LOAN
        return IncomeMultiplier.multiply(loan, income)
    }
}