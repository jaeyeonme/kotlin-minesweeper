package minesweeper.view

import minesweeper.domain.BoardSize
import minesweeper.domain.CellContent
import minesweeper.domain.MineCount
import minesweeper.domain.MinePlacementStrategy
import minesweeper.domain.MinePositions
import minesweeper.domain.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.ArrayDeque

class InputViewTest {
    @Test
    fun `높이와 너비와 지뢰 개수를 순서대로 입력받는다`() {
        val inputs = ArrayDeque(listOf("2", "3", "2"))
        val prompts = mutableListOf<String>()
        val inputView =
            InputView(
                readLine = inputs::removeFirst,
                writeLine = { prompt -> prompts.add(prompt) },
            )

        val configuration = inputView.readConfiguration()
        val board = configuration.createBoard(fixedPlacement())
        val snapshot = board.snapshot()

        assertThat(snapshot.cellCount()).isEqualTo(6)
        assertThat(snapshot.mineCount()).isEqualTo(2)
        assertThat(snapshot.contentAt(Position(0, 0))).isEqualTo(CellContent.MINE)
        assertThat(prompts)
            .containsExactly("높이를 입력하세요.", "너비를 입력하세요.", "지뢰는 몇 개인가요?")
    }

    @Test
    fun `쉼표로 구분한 가로와 세로 좌표를 1부터 시작하는 위치로 입력받는다`() {
        val prompts = mutableListOf<String>()
        val inputView =
            InputView(
                readLine = { "2, 3" },
                writeLine = { prompt -> prompts.add(prompt) },
            )

        val result = inputView.readPosition()

        assertThat(result).isEqualTo(Position(1, 2))
        assertThat(prompts).containsExactly("open:")
    }

    private fun fixedPlacement(): MinePlacementStrategy =
        MinePlacementStrategy { _: BoardSize, _: MineCount ->
            MinePositions(listOf(Position(0, 0), Position(2, 1)))
        }
}
