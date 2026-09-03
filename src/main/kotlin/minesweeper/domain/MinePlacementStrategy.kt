package minesweeper.domain

fun interface MinePlacementStrategy {
    fun select(
        boardSize: BoardSize,
        mineCount: MineCount,
    ): MinePositions
}
