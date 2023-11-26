package domain

import enum.CellStatus
import inteface.MinePlacementStrategy

class GameBoard(val height: Int, val width: Int, private val minePlacementStrategy: MinePlacementStrategy) {
    private val board = Board(height, width)

    fun placeMines(mineCount: Int) {
        val minePositions = minePlacementStrategy.placeMines(height, width, mineCount)
        minePositions.forEach { board.placeMineAt(it) }
    }

    fun countMines(): Int = board.countMines()

    fun forEachCell(onEachCell: (Position, CellStatus) -> Unit) {
        for (row in 0 until height) {
            processRow(row, onEachCell)
        }
    }

    private fun processRow(row: Int, onEachCell: (Position, CellStatus) -> Unit) {
        for (column in 0 until width) {
            val position = Position(column, row)
            onEachCell(position, if (hasMineAt(position)) CellStatus.MINE else CellStatus.EMPTY)
        }
    }

    private fun hasMineAt(position: Position): Boolean = board.hasMineAt(position)
}
