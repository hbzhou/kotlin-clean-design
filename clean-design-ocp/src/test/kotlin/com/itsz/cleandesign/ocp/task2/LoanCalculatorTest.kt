package com.itsz.cleandesign.ocp.task2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class LoanCalculatorTest {

    private lateinit var calculator: LoanCalculator

    @Test
    fun studentLoanForYoungPoorPerson() {
        calculator = StudentLoanCalculator()
        assertEquals(calculator.calculate(18, 100), 100)
    }

    @Test
    fun studentLoanForOldPerson() {
        calculator = StudentLoanCalculator()
        assertEquals(calculator.calculate(21, 100), 250)
    }

    @Test
    fun studentLoanForRichPerson() {
        calculator = StudentLoanCalculator()
        assertEquals(calculator.calculate(18, 2000), 200)
    }

    @Test
    fun carLoanForYoungPoorPerson() {
        calculator = CarLoanCalculator()
        assertEquals(calculator.calculate(20, 500), 2000)
    }

    @Test
    fun carLoanForAdultPoorPerson() {
        calculator = CarLoanCalculator()
        assertEquals(calculator.calculate(45, 500), 3000)
    }

    @Test
    fun carLoanForOldPoorPerson() {
        calculator = CarLoanCalculator()
        assertEquals(calculator.calculate(60, 500), 3500)
    }

    @Test
    fun carLoanForYoungRichPerson() {
        calculator = CarLoanCalculator()
        assertEquals(calculator.calculate(20, 2000), 4000)
    }

    @Test
    fun carLoanForAdultRichPerson() {
        calculator = CarLoanCalculator()
        assertEquals(calculator.calculate(45, 2000), 6000)
    }

    @Test
    fun carLoanForOldRichPerson() {
        calculator = CarLoanCalculator()
        assertEquals(calculator.calculate(60, 2000), 7000)
    }

    @Test
    fun houseLoanForYoungPoorPerson() {
        calculator = HouseLoanCalculator()
        assertEquals(calculator.calculate(20, 500), 100000)
    }

    @Test
    fun houseLoanForOldPoorPerson() {
        calculator = HouseLoanCalculator()
        assertEquals(calculator.calculate(60, 500), 100000)
    }

    @Test
    fun houseLoanForYoungRichPerson() {
        calculator = HouseLoanCalculator()
        assertEquals(calculator.calculate(20, 65000), 200000)
    }

    @Test
    fun houseLoanForOldRichPerson() {
        calculator = HouseLoanCalculator()
        assertEquals(calculator.calculate(60, 65000), 400000)
    }
}