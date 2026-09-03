package minesweeper.domain

class BoardSnapshot(
    private val boardSize: BoardSize,
    private val cells: CellSnapshots,
) {
    fun cellCount(): Int = cells.count()

    fun mineCount(): Int = cells.countMines()

    fun contentAt(position: Position): CellContent = find(position).mapContent { content -> content }

    fun adjacentMineCountAt(position: Position): AdjacentMineCount {
        require(boardSize.contains(position)) { "조회할 위치는 보드 범위 안에 있어야 합니다." }
        val neighborPositions = NeighborPositions.around(position, boardSize)
        val count = neighborPositions.countMatching(::isMineAt)
        return AdjacentMineCount(count)
    }

    fun <T> mapRows(
        onClosed: () -> T,
        onMine: () -> T,
        onSafe: (AdjacentMineCount) -> T,
    ): List<List<T>> {
        val values = cells.map { cell -> mapCell(cell, onClosed, onMine, onSafe) }
        return boardSize.splitIntoRows(values)
    }

    private fun isMineAt(position: Position): Boolean = find(position).isMine()

    private fun find(position: Position): CellSnapshot = cells.find(position)

    private fun <T> mapCell(
        cell: CellSnapshot,
        onClosed: () -> T,
        onMine: () -> T,
        onSafe: (AdjacentMineCount) -> T,
    ): T =
        cell.mapByState(
            onClosed = onClosed,
            onMine = onMine,
            onSafe = { position -> onSafe(adjacentMineCountAt(position)) },
        )
}
