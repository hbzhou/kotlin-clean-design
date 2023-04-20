package com.itsz.cleandesign.ocp.task2

class HouseLoanCalculator: LoanCalculator {
    companion object{
        const val BASE_LOAN = 100000
        const val ADULT_AGE = 30
    }
    override fun calculate(age: Int, income: Int): Int {
        val loan = if (age > ADULT_AGE && income > BASE_LOAN / 2) BASE_LOAN *2 else BASE_LOAN
        return IncomeMultiplier.multiply(loan, income)
    }
}