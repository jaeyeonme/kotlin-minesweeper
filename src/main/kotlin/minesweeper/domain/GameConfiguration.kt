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
        val selectedMineCount = minePositions.count()
        require(mineCount.matches(selectedMineCount)) { "지뢰 개수가 게임 설정과 일치하지 않습니다." }
        return Board.create(boardSize, minePositions)
    }

    fun createGame(minePlacementStrategy: MinePlacementStrategy): Game {
        val board = createBoard(minePlacementStrategy)
        return Game.start(board)
    }
}
