package com.itsz.cleandesign.ocp.task2


class CarLoanCalculator : LoanCalculator {
    companion object {
        const val BASE_LOAN = 2000
        const val ADULT_AGE = 30
        const val OLD_AGE = 50
        const val OLD_AGE_EXTRA_AMOUNT = 1500
        const val ADULT_AGE_EXTRA_AMOUNT = 1000
    }

    override fun calculate(age: Int, income: Int): Int {
        val loan = when {
            age > OLD_AGE -> BASE_LOAN + OLD_AGE_EXTRA_AMOUNT
            age in ADULT_AGE .. OLD_AGE -> BASE_LOAN + ADULT_AGE_EXTRA_AMOUNT
            else -> BASE_LOAN
        }
        return IncomeMultiplier.multiply(loan, income)
    }
}