package minesweeper.domain

class Cells private constructor(
    private val values: List<Cell>,
) {
    fun placeMines(minePositions: MinePositions) {
        minePositions.forEach { position ->
            find(position).placeMine()
        }
    }

    fun snapshots(): List<CellSnapshot> = values.map(Cell::snapshot)

    private fun find(position: Position): Cell =
        values.first { cell ->
            cell.matches(position)
        }

    companion object {
        fun create(boardSize: BoardSize): Cells {
            val positions = boardSize.positions()
            val cells = positions.map(::Cell)
            return Cells(cells)
        }
    }
}
