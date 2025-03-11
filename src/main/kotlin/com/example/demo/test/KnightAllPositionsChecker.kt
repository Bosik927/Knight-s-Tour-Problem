package com.example.demo.test

fun main(args: Array<String>) {
    KnightAllPositionsChecker().checkAllPositions()
}

class KnightAllPositionsChecker(
    private val width: Int = 8,
    private val height: Int = 8
) {


    fun checkAllPositions(): Boolean {
        if (width < 3 || height < 3) {
            println("Board is too small")
            return false;
        }
        val calculator = KnightPositionsCalculator(width, height)

        for (x in 0 until width) {
            for (y in 0 until height) {
                println("Checking for starting position: $x, $y")
                val visitedBoard = Array(width) { IntArray(height) }
                visitedBoard[x][y] = 1
                deepCalculation(visitedBoard, calculator, x, y)
            }
        }

        return false;
    }


    fun deepCalculation(visitedBoard: Array<IntArray>, calculator: KnightPositionsCalculator, x: Int, y: Int) {
        if (visitedBoard.all { it.all { it > 0 } }) {
            println("All positions visited")
            println(visitedBoard)
            return
        }

        val possiblePositions = calculator.calculate(x, y)

        if (possiblePositions.isEmpty()) {
            println("No more possible moves")
            return
        }

        possiblePositions.forEach {
            //Jump
            val copy = visitedBoard.copyOf()
            copy[it.first][it.second] = 1
            deepCalculation(
                copy,
                calculator,
                it.first,
                it.second
            )
        }
    }
}


fun calculatePossibleKnightPositions(
    visitedBoard: Array<BooleanArray>,
    x: Int,
    y: Int,
    width: Int,
    height: Int
): List<Pair<Int, Int>> {
    val positions = mutableListOf<Pair<Int, Int>>()
    calcPosition1(visitedBoard, x, y, width, height)?.let { positions.add(it) }
    calcPosition2(visitedBoard, x, y, width, height)?.let { positions.add(it) }
    calcPosition3(visitedBoard, x, y, width, height)?.let { positions.add(it) }
    calcPosition4(visitedBoard, x, y, width, height)?.let { positions.add(it) }
    calcPosition5(visitedBoard, x, y, width, height)?.let { positions.add(it) }
    calcPosition6(visitedBoard, x, y, width, height)?.let { positions.add(it) }
    calcPosition7(visitedBoard, x, y, width, height)?.let { positions.add(it) }
    calcPosition8(visitedBoard, x, y, width, height)?.let { positions.add(it) }
    return positions;
}

fun calcPosition1(visitedBoard: Array<BooleanArray>, x: Int, y: Int, width: Int, height: Int): Pair<Int, Int>? {
    val position1X = x - 2
    val position2Y = y - 1;

    if (isPossibleSquare(visitedBoard, position1X, position2Y, width, height)) {
        return Pair(position1X, position2Y)
    }
    return null
}

fun calcPosition2(visitedBoard: Array<BooleanArray>, x: Int, y: Int, width: Int, height: Int): Pair<Int, Int>? {
    val position1X = x - 1
    val position2Y = y - 2;

    if (isPossibleSquare(visitedBoard, position1X, position2Y, width, height)) {
        return Pair(position1X, position2Y)
    }
    return null
}

fun calcPosition3(visitedBoard: Array<BooleanArray>, x: Int, y: Int, width: Int, height: Int): Pair<Int, Int>? {
    val position1X = x - 2
    val position2Y = y + 1
    if (isPossibleSquare(visitedBoard, position1X, position2Y, width, height)) {
        return Pair(position1X, position2Y)
    }
    return null
}

fun calcPosition4(visitedBoard: Array<BooleanArray>, x: Int, y: Int, width: Int, height: Int): Pair<Int, Int>? {
    val position1X = x - 1
    val position2Y = y + 2
    if (isPossibleSquare(visitedBoard, position1X, position2Y, width, height)) {
        return Pair(position1X, position2Y)
    }
    return null
}

fun calcPosition5(visitedBoard: Array<BooleanArray>, x: Int, y: Int, width: Int, height: Int): Pair<Int, Int>? {
    val position1X = x + 1
    val position2Y = y + 2
    if (isPossibleSquare(visitedBoard, position1X, position2Y, width, height)) {
        return Pair(position1X, position2Y)
    }
    return null
}

fun calcPosition6(visitedBoard: Array<BooleanArray>, x: Int, y: Int, width: Int, height: Int): Pair<Int, Int>? {
    val position1X = x + 2
    val position2Y = y + 1
    if (isPossibleSquare(visitedBoard, position1X, position2Y, width, height)) {
        return Pair(position1X, position2Y)
    }
    return null
}

fun calcPosition7(visitedBoard: Array<BooleanArray>, x: Int, y: Int, width: Int, height: Int): Pair<Int, Int>? {
    val position1X = x + 2
    val position2Y = y - 1
    if (isPossibleSquare(visitedBoard, position1X, position2Y, width, height)) {
        return Pair(position1X, position2Y)
    }
    return null
}

fun calcPosition8(visitedBoard: Array<BooleanArray>, x: Int, y: Int, width: Int, height: Int): Pair<Int, Int>? {
    val position1X = x + 1
    val position2Y = y - 2
    if (isPossibleSquare(visitedBoard, position1X, position2Y, width, height)) {
        return Pair(position1X, position2Y)
    }
    return null
}

private fun isPossibleSquare(
    visitedBoard: Array<BooleanArray>,
    position1X: Int,
    position2Y: Int,
    width: Int,
    height: Int
): Boolean {
    return position1X > 0 && position2Y > 0 && position1X < width && position2Y < height
            && !visitedBoard[position1X][position2Y]
}

