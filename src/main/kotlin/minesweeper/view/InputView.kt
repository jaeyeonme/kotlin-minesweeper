package minesweeper.view

import minesweeper.domain.BoardSize
import minesweeper.domain.GameConfiguration
import minesweeper.domain.MineCount
import minesweeper.domain.Position

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

    fun readPosition(): Position {
        writeLine(OPEN_PROMPT)
        val input = readLine()
        return parsePosition(input)
    }

    private fun readNumber(prompt: String): Int {
        writeLine(prompt)
        val input = readLine()
        return input.toInt()
    }

    private fun parsePosition(input: String): Position {
        val coordinates = input.split(COORDINATE_DELIMITER)
        require(coordinates.size == COORDINATE_COUNT) { "좌표는 가로와 세로를 쉼표로 구분해야 합니다." }
        val horizontal = parseCoordinate(coordinates.first())
        val vertical = parseCoordinate(coordinates.last())
        return Position.fromOneBased(horizontal, vertical)
    }

    private fun parseCoordinate(input: String): Int {
        val trimmedInput = input.trim()
        return trimmedInput.toInt()
    }

    private companion object {
        const val OPEN_PROMPT = "open:"
        const val COORDINATE_DELIMITER = ","
        const val COORDINATE_COUNT = 2
    }
}
