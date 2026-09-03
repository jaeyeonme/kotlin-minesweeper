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
    fun `지뢰와 주변 지뢰 개수를 공백으로 구분해 행과 열에 맞춰 출력한다`() {
        val configuration = GameConfiguration(BoardSize(height = 3, width = 3), MineCount(1))
        val board = configuration.createBoard(fixedPlacement(Position(0, 0)))
        val outputView = OutputView(writeLine = {})

        val result = outputView.render(board.snapshot())

        assertThat(result).isEqualTo("* 1 0\n1 1 0\n0 0 0")
    }

    private fun fixedPlacement(vararg positions: Position): MinePlacementStrategy =
        MinePlacementStrategy { _, _ -> MinePositions(positions.toList()) }
}
