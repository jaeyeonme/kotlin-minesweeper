package minesweeper.domain

class GameConfiguration(
    private val boardSize: BoardSize,
    private val mineCount: MineCount,
) {
    init {
        require(boardSize.canContain(mineCount)) { "지뢰 개수는 전체 칸 수 이하여야 합니다." }
    }

    fun createBoard(minePlacementStrategy: MinePlacementStrategy): Board {
        val minePositions = minePlacementStrategy.select(boardSize, mineCount)
        require(mineCount.matches(minePositions.count())) { "지뢰 개수가 게임 설정과 일치하지 않습니다." }
        return Board.create(boardSize, minePositions)
    }
}
