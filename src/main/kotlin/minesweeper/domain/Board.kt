package minesweeper.domain

class Board private constructor(
    private val boardSize: BoardSize,
    private val cells: Cells,
) {
    fun snapshot(): BoardSnapshot = BoardSnapshot(boardSize, cells.snapshots())

    fun open(position: Position) {
        require(boardSize.contains(position)) { "열 위치는 보드 범위 안에 있어야 합니다." }
        if (cells.isMineAt(position)) {
            cells.open(position)
            return
        }
        openSafeArea(position)
    }

    fun hasOpenedMine(): Boolean = cells.hasOpenedMine()

    fun areAllSafeCellsOpen(): Boolean = cells.areAllSafeCellsOpen()

    private fun openSafeArea(position: Position) {
        val positionsToOpen = PositionsToOpen(position)
        while (positionsToOpen.hasNext()) {
            openNextSafePosition(positionsToOpen)
        }
    }

    private fun openNextSafePosition(positionsToOpen: PositionsToOpen) {
        val position = positionsToOpen.removeFirst()
        if (!cells.canOpenSafely(position)) {
            return
        }
        cells.open(position)
        if (hasAdjacentMine(position)) {
            return
        }
        val neighborPositions = NeighborPositions.around(position, boardSize)
        positionsToOpen.addAll(neighborPositions)
    }

    private fun hasAdjacentMine(position: Position): Boolean {
        val neighborPositions = NeighborPositions.around(position, boardSize)
        val adjacentMineCount = neighborPositions.countMatching(cells::isMineAt)
        return adjacentMineCount > 0
    }

    companion object {
        fun create(
            boardSize: BoardSize,
            minePositions: MinePositions,
        ): Board {
            require(minePositions.areWithin(boardSize)) { "지뢰 위치는 보드 범위 안에 있어야 합니다." }
            val cells = Cells.create(boardSize)
            cells.placeMines(minePositions)
            return Board(boardSize, cells)
        }
    }
}
