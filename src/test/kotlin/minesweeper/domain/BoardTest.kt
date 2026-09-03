package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `입력한 높이와 너비에 맞춰 칸을 생성한다`() {
        val configuration = GameConfiguration(BoardSize(height = 2, width = 3), MineCount(2))
        val board = configuration.createBoard(fixedPlacement(Position(0, 0), Position(2, 1)))

        assertThat(board.snapshot().cellCount()).isEqualTo(6)
    }

    @Test
    fun `서로 다른 위치에 입력한 개수만큼 지뢰를 배치한다`() {
        val configuration = GameConfiguration(BoardSize(height = 2, width = 3), MineCount(2))
        val board = configuration.createBoard(fixedPlacement(Position(0, 0), Position(2, 1)))
        val snapshot = board.snapshot()

        assertThat(snapshot.mineCount()).isEqualTo(2)
        assertThat(snapshot.contentAt(Position(0, 0))).isEqualTo(CellContent.MINE)
        assertThat(snapshot.contentAt(Position(2, 1))).isEqualTo(CellContent.MINE)
        assertThat(snapshot.contentAt(Position(1, 0))).isEqualTo(CellContent.SAFE)
    }

    private fun fixedPlacement(vararg positions: Position): MinePlacementStrategy =
        MinePlacementStrategy { _, _ -> MinePositions(positions.toList()) }
}
