package minesweeper.domain

class Game private constructor(
    private val board: Board,
    private var status: GameStatus = GameStatus.IN_PROGRESS,
) {
    fun open(position: Position) {
        require(isInProgress()) { "진행 중인 게임에서만 칸을 열 수 있습니다." }
        board.open(position)
        status = determineStatus()
    }

    fun isInProgress(): Boolean = status == GameStatus.IN_PROGRESS

    fun isWon(): Boolean = status == GameStatus.WON

    fun isLost(): Boolean = status == GameStatus.LOST

    fun snapshot(): BoardSnapshot = board.snapshot()

    private fun determineStatus(): GameStatus =
        when {
            board.hasOpenedMine() -> GameStatus.LOST
            board.areAllSafeCellsOpen() -> GameStatus.WON
            else -> GameStatus.IN_PROGRESS
        }

    companion object {
        fun start(board: Board): Game = Game(board)
    }
}
