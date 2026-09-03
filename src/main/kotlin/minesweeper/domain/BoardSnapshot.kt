package minesweeper.domain

class BoardSnapshot(
    private val boardSize: BoardSize,
    private val cells: List<CellSnapshot>,
) {
    fun cellCount(): Int = cells.size

    fun mineCount(): Int = cells.count(CellSnapshot::isMine)

    fun contentAt(position: Position): CellContent = find(position).mapContent { content -> content }

    fun adjacentMineCountAt(position: Position): AdjacentMineCount {
        require(boardSize.contains(position)) { "조회할 위치는 보드 범위 안에 있어야 합니다." }
        val neighborPositions = NeighborPositions.around(position, boardSize)
        val count = neighborPositions.countMatching(::isMineAt)
        return AdjacentMineCount(count)
    }

    fun mapRows(transform: (CellContent) -> String): List<List<String>> {
        val symbols = cells.map { cell -> cell.mapContent(transform) }
        return boardSize.splitIntoRows(symbols)
    }

    private fun isMineAt(position: Position): Boolean = find(position).isMine()

    private fun find(position: Position): CellSnapshot =
        cells.first { cell ->
            cell.matches(position)
        }
}
