package minesweeper.domain

class Board private constructor(
    private val boardSize: BoardSize,
    private val cells: Cells,
) {
    fun snapshot(): BoardSnapshot = BoardSnapshot(boardSize, cells.snapshots())

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
