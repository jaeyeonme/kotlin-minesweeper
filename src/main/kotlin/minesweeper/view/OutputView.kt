package minesweeper.view

import minesweeper.domain.AdjacentMineCount
import minesweeper.domain.BoardSnapshot

class OutputView(
    private val writeLine: (String) -> Unit = ::println,
) {
    fun display(snapshot: BoardSnapshot) {
        writeLine(GAME_START_MESSAGE)
        val board = render(snapshot)
        writeLine(board)
    }

    fun render(snapshot: BoardSnapshot): String {
        val rows =
            snapshot.mapRows(
                onMine = { MINE_SYMBOL },
                onSafe = AdjacentMineCount::toString,
            )
        val lines = rows.map(::renderRow)
        return lines.joinToString("\n")
    }

    private fun renderRow(row: List<String>): String = row.joinToString(" ")

    private companion object {
        const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"
        const val MINE_SYMBOL = "*"
    }
}
