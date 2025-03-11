package com.example.demo.test

fun main(args: Array<String>) {
    KnightAllPositionsChecker().checkAllPositions()
}

class KnightAllPositionsChecker(
    private val width: Int = 8, private val height: Int = 8
) {


    fun checkAllPositions() {
        if (width < 3 || height < 3) {
            println("Board is too small")
            return
        }
        val calculator = KnightPositionsCalculator(width, height)

        val startTime = System.currentTimeMillis()
        for (x in 0 until width) {
            for (y in 0 until height) {
                println("Checking for starting position: $x, $y")
                val visitedBoard = Array(width) { IntArray(height) }
                visitedBoard[x][y] = 1
                deepCalculation(visitedBoard, calculator, x, y, 1)
            }
        }
        val endTime = System.currentTimeMillis()
        println("Time taken: ${endTime - startTime} ms")

        return
    }


    fun deepCalculation(
        visitedBoard: Array<IntArray>, calculator: KnightPositionsCalculator, x: Int, y: Int, count: Int
    ) {
        if (visitedBoard.all { it.all { it > 0 } }) {
            println("All positions visited")
            println(visitedBoard)
            return
        }

        val possiblePositions =
            calculator.calculate(x + 1, y + 1).filter { (nx, ny) -> visitedBoard[nx - 1][ny - 1] == 0 }
        if (possiblePositions.isEmpty()) {
            println("No more possible moves")
            return
        }

        possiblePositions.forEach {
            //Jump
            val copy = visitedBoard.copyOf()
            val americanX = it.first - 1
            val americanY = it.second - 1
            copy[americanX][americanY] = count
            deepCalculation(
                copy, calculator, americanX, americanY, count + 1
            )
        }
    }
}
