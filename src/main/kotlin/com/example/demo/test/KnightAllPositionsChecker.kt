package com.example.demo.test

fun main(args: Array<String>) {
    println(KnightAllPositionsChecker().checkAllPositions())
}

class KnightAllPositionsChecker(
    private val width: Int = 8, private val height: Int = 8
) {

    fun checkAllPositions(): Boolean {
        if (width < 2 || height < 2) {
            println("Board is too small")
            return false
        }
        val calculator = KnightPositionsCalculator(width, height)
        val startTime = System.currentTimeMillis()

        for (x in 0 until width) {
            for (y in 0 until height) {
                val visitedBoard = Array(width) { IntArray(height) }
                visitedBoard[x][y] = 1

                println("Checking position ($x, $y)")
                if (deepCalculation(visitedBoard.copyOf(), calculator, x, y, 2)) {
                    val endTime = System.currentTimeMillis()
                    println("Time: ${endTime - startTime}ms")
                    return true
                }

            }
        }

        return false
    }


    private fun deepCalculation(
        visitedBoard: Array<IntArray>, calculator: KnightPositionsCalculator, x: Int, y: Int, count: Int
    ): Boolean {
        if (visitedBoard.all { it.all { it > 0 } }) {
            printBoard(visitedBoard)
            return true
        }

        val possiblePositions =
            calculator.calculate(x + 1, y + 1)
                .filter { (nx, ny) -> visitedBoard[nx - 1][ny - 1] == 0 }
        if (possiblePositions.isEmpty()) {
            return false
        }

        possiblePositions.forEach {
            //Jump
            val copy = visitedBoard.map { it.clone() }.toTypedArray()
            val americanX = it.first - 1
            val americanY = it.second - 1
            copy[americanX][americanY] = count
            if (deepCalculation(copy, calculator, americanX, americanY, count + 1)) {
                return true
            }
        }
        return false
    }

    private fun printBoard(board: Array<IntArray>) {
        println("Board (${board.size}x${board[0].size}):")
        board.forEach { row ->
            println(row.joinToString(" ") { "%2d".format(it) }) // Aligns numbers with spacing
        }
    }
}
