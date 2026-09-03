package minesweeper.domain

class BoardSnapshot(
    private val boardSize: BoardSize,
    private val cells: List<CellSnapshot>,
) {
    fun cellCount(): Int = cells.size

    fun mineCount(): Int = cells.count(CellSnapshot::isMine)

    fun contentAt(position: Position): CellContent = find(position).mapContent { content -> content }

    fun isOpenAt(position: Position): Boolean {
        require(boardSize.contains(position)) { "조회할 위치는 보드 범위 안에 있어야 합니다." }
        return find(position).isOpen()
    }

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

    private fun find(position: Position): CellSnapshot =
        cells.first { cell ->
            cell.matches(position)
        }

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
