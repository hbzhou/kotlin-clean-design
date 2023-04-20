package com.itsz.cleandesign.ocp.task2

object IncomeMultiplier {

    fun multiply(load: Int, income: Int): Int = load * getIncomeMultiplier(income)

    private fun getIncomeMultiplier(income: Int): Int = if (income <= 1_000) 1 else 2

}