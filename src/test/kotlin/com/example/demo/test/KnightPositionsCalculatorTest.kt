package com.example.demo.test

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class KnightPositionsCalculatorTest {


    private val calculator2x2 = KnightPositionsCalculator(2, 2)
    private val calculator3x3 = KnightPositionsCalculator(3, 3)
    private val calculator8x8 = KnightPositionsCalculator(8, 8)

    @Test
    fun `test knight moves from valid position (1,1) on 2x2 board`() {
        val expectedMoves = emptySet<Pair<Int, Int>>()
        val actualMoves = calculator2x2.calculate(1, 1).toSet()
        assertEquals(expectedMoves, actualMoves)
    }

    @Test
    fun `test knight moves from valid position (1,1) on 3x3 board`() {
        val expectedMoves = setOf(
            2 to 3,
            3 to 2
        )
        val actualMoves = calculator3x3.calculate(1, 1).toSet()
        assertEquals(expectedMoves, actualMoves)
    }

    @Test
    fun `test knight moves from valid position (6,3) on 8x8 board`() {
        val expectedMoves = setOf(
            4 to 2,
            4 to 4,
            5 to 1,
            5 to 5,
            7 to 1,
            7 to 5,
            8 to 2,
            8 to 4
        )
        val actualMoves = calculator8x8.calculate(6, 3).toSet()
        assertEquals(expectedMoves, actualMoves)
    }

    @Test
    fun `test knight moves from valid position (2,2) on 8x8 board`() {
        val expectedMoves = setOf(
            4 to 1,
            4 to 3,
            3 to 4,
            1 to 4
        )
        val actualMoves = calculator8x8.calculate(2, 2).toSet()
        assertEquals(expectedMoves, actualMoves)
    }


}