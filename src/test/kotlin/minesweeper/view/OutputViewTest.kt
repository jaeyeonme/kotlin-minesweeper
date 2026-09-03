package minesweeper.view

import minesweeper.domain.BoardSize
import minesweeper.domain.GameConfiguration
import minesweeper.domain.MineCount
import minesweeper.domain.MinePlacementStrategy
import minesweeper.domain.MinePositions
import minesweeper.domain.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OutputViewTest {
    @Test
    fun `열리지 않은 칸은 지뢰 여부와 관계없이 C로 출력한다`() {
        val game = createGame(BoardSize(height = 2, width = 2), Position(0, 0))
        val outputView = OutputView(writeLine = {})

        val result = outputView.render(game.snapshot())

        assertThat(result).isEqualTo("C C\nC C")
    }

    @Test
    fun `열린 안전 칸은 주변 지뢰 개수로 출력한다`() {
        val game = createGame(BoardSize(height = 2, width = 2), Position(1, 1))
        val outputView = OutputView(writeLine = {})
        game.open(Position(0, 0))

        val result = outputView.render(game.snapshot())

        assertThat(result).isEqualTo("1 C\nC C")
    }

    @Test
    fun `열린 지뢰 칸은 별표로 출력한다`() {
        val minePosition = Position(1, 0)
        val game = createGame(BoardSize(height = 1, width = 2), minePosition)
        val outputView = OutputView(writeLine = {})
        game.open(minePosition)

        val result = outputView.render(game.snapshot())

        assertThat(result).isEqualTo("C *")
    }

    @Test
    fun `게임 시작과 패배 메시지를 출력한다`() {
        val outputs = mutableListOf<String>()
        val outputView = OutputView(writeLine = outputs::add)

        outputView.displayStart()
        outputView.displayLoss()

        assertThat(outputs).containsExactly("지뢰찾기 게임 시작", "Lose Game.")
    }

    private fun createGame(
        boardSize: BoardSize,
        minePosition: Position,
    ) = GameConfiguration(boardSize, MineCount(1)).createGame(fixedPlacement(minePosition))

    private fun fixedPlacement(position: Position): MinePlacementStrategy =
        MinePlacementStrategy { _, _ -> MinePositions(listOf(position)) }
}
