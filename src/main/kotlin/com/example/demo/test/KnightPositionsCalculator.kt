package com.example.demo.test

class KnightPositionsCalculator(
    private val boardWidth: Int,
    private val boardHeight: Int
) {

    private val knightMoves = listOf(
        //x to y
        -2 to -1,
        -1 to -2,
        -2 to 1,
        -1 to 2,
        1 to 2,
        2 to 1,
        2 to -1,
        1 to -2
    )

    fun calculate(x: Int, y: Int): List<Pair<Int, Int>> {
        validate(x, y)
        return knightMoves.map { (dx, dy) -> x + dx to y + dy }
            .filter { (nx, ny) -> isLegalSquare(nx, ny) }
    }

    private fun validate(x: Int, y: Int) {
        if (!isLegalSquare(x, y)) {
            throw IllegalArgumentException("Invalid position ($x, $y) is outside board limits.")
        }
    }

    private fun isLegalSquare(x: Int, y: Int): Boolean {
        return (x in 1..boardWidth) && (y in 1..boardHeight)
    }
}