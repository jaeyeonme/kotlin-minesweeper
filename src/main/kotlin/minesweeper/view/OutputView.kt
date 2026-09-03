package minesweeper.view

import minesweeper.domain.BoardSnapshot
import minesweeper.domain.CellContent

class OutputView(
    private val writeLine: (String) -> Unit = ::println,
) {
    fun display(snapshot: BoardSnapshot) {
        writeLine(GAME_START_MESSAGE)
        val board = render(snapshot)
        writeLine(board)
    }

    fun render(snapshot: BoardSnapshot): String {
        val rows = snapshot.mapRows(::symbolFor)
        val lines = rows.map(::renderRow)
        return lines.joinToString("\n")
    }

    private fun renderRow(row: List<String>): String = row.joinToString(" ")

    private fun symbolFor(content: CellContent): String =
        when (content) {
            CellContent.MINE -> "*"
            CellContent.SAFE -> "C"
        }

    private companion object {
        const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"
    }
}
