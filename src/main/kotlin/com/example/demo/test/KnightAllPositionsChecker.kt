package com.example.demo.test

fun main(args: Array<String>) {
    println(KnightAllPositionsChecker().checkAllPositions())
}

class KnightAllPositionsChecker(
    private val width: Int = 100, private val height: Int = 19
) {

    //Primitive brute force:
    // 45ms to 95ms for 8x8
    // 141ms to 174ms for 12x12
    // 0.9s to 1.1s for 20x20

    //Optimized brute force:
    // 40 ms for 20x20
    // 40 ms for 25x25
    // 100 ms for 40x40
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

                if (deepCalculation(visitedBoard, calculator, x, y, 2)) {
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
            calculator.calculate(x + 1, y + 1).filter { (nx, ny) -> visitedBoard[nx - 1][ny - 1] == 0 }
        if (possiblePositions.isEmpty()) {
            return false
        }

        possiblePositions.forEach {
            //Jump
            val copy = visitedBoard.copyOf()
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
