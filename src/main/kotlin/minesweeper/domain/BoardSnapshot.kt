package minesweeper.domain

class BoardSnapshot(
    private val boardSize: BoardSize,
    private val cells: List<CellSnapshot>,
) {
    fun cellCount(): Int = cells.size

    fun mineCount(): Int = cells.count(CellSnapshot::isMine)

    fun contentAt(position: Position): CellContent =
        cells
            .first { cell -> cell.matches(position) }
            .mapContent { content -> content }

    fun mapRows(transform: (CellContent) -> String): List<List<String>> {
        val symbols = cells.map { cell -> cell.mapContent(transform) }
        return boardSize.splitIntoRows(symbols)
    }
}
