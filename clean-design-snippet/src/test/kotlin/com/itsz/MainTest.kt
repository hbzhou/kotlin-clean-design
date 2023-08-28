package com.itsz

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    fun calc () {
        val a = 3
        val b = 5
        Assertions.assertEquals(8, a + b)
    }
}