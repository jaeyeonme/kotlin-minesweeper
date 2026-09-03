package minesweeper.domain

class CellSnapshots(
    private val values: List<CellSnapshot>,
) {
    fun count(): Int = values.size

    fun countMines(): Int = values.count(CellSnapshot::isMine)

    fun find(position: Position): CellSnapshot =
        values.first { cell ->
            cell.matches(position)
        }

    fun <T> map(transform: (CellSnapshot) -> T): List<T> = values.map(transform)
}
