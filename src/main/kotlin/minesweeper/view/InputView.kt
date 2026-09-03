package minesweeper.view

import minesweeper.domain.BoardSize
import minesweeper.domain.GameConfiguration
import minesweeper.domain.MineCount

class InputView(
    private val readLine: () -> String = ::readln,
    private val writeLine: (String) -> Unit = ::println,
) {
    fun readConfiguration(): GameConfiguration {
        val height = readNumber("높이를 입력하세요.")
        val width = readNumber("너비를 입력하세요.")
        val mineCount = readNumber("지뢰는 몇 개인가요?")
        return GameConfiguration(BoardSize(height, width), MineCount(mineCount))
    }

    private fun readNumber(prompt: String): Int {
        writeLine(prompt)
        val input = readLine()
        return input.toInt()
    }
}
