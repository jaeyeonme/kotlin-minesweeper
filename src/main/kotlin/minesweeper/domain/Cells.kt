package minesweeper.domain

class Cells private constructor(
    private val values: List<Cell>,
) {
    fun placeMines(minePositions: MinePositions) {
        minePositions.forEach { position ->
            find(position).placeMine()
        }
    }

    fun snapshots(): CellSnapshots {
        val snapshots = values.map(Cell::snapshot)
        return CellSnapshots(snapshots)
    }

    fun open(position: Position) {
        find(position).open()
    }

    fun isMineAt(position: Position): Boolean = find(position).isMine()

    fun canOpenSafely(position: Position): Boolean {
        val cell = find(position)
        return !cell.isMine() && !cell.isOpen()
    }

    fun areAllSafeCellsOpen(): Boolean = values.all { cell -> cell.isMine() || cell.isOpen() }

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
