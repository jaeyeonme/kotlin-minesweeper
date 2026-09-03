package minesweeper.view

import minesweeper.domain.AdjacentMineCount
import minesweeper.domain.BoardSnapshot

class OutputView(
    private val writeLine: (String) -> Unit = ::println,
) {
    fun displayStart() {
        writeLine(GAME_START_MESSAGE)
    }

    fun display(snapshot: BoardSnapshot) {
        val board = render(snapshot)
        writeLine(board)
    }

    fun displayLoss() {
        writeLine(LOSS_MESSAGE)
    }

    fun render(snapshot: BoardSnapshot): String {
        val rows =
            snapshot.mapRows(
                onClosed = { CLOSED_SYMBOL },
                onMine = { MINE_SYMBOL },
                onSafe = AdjacentMineCount::toString,
            )
        val lines = rows.map(::renderRow)
        return lines.joinToString("\n")
    }

    private fun renderRow(row: List<String>): String = row.joinToString(" ")

    private companion object {
        const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"
        const val LOSS_MESSAGE = "Lose Game."
        const val CLOSED_SYMBOL = "C"
        const val MINE_SYMBOL = "*"
    }
}
