package minesweeper.domain

class RandomMinePlacementStrategy : MinePlacementStrategy {
    override fun select(
        boardSize: BoardSize,
        mineCount: MineCount,
    ): MinePositions {
        val positions = boardSize.positions()
        val shuffledPositions = positions.shuffled()
        val selectedPositions = mineCount.takeFrom(shuffledPositions)
        return MinePositions(selectedPositions)
    }
}
