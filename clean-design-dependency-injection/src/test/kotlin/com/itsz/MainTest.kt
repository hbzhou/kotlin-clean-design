package com.itsz

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class MainTest {

    @Test
    fun calcAdd (){
        val a = 1
        val b = 3
        Assertions.assertEquals(4, a + b)
    }
}