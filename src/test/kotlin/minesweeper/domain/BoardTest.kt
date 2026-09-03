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

    @Test
    fun `가로와 세로와 대각선의 지뢰 개수를 계산한다`() {
        val minePositions =
            arrayOf(
                Position(0, 0),
                Position(1, 0),
                Position(2, 0),
                Position(0, 1),
                Position(2, 1),
                Position(0, 2),
                Position(1, 2),
                Position(2, 2),
            )
        val configuration = GameConfiguration(BoardSize(height = 3, width = 3), MineCount(8))
        val board = configuration.createBoard(fixedPlacement(*minePositions))

        val result = board.snapshot().adjacentMineCountAt(Position(1, 1))

        assertThat(result).isEqualTo(AdjacentMineCount(8))
    }

    @Test
    fun `모서리는 보드 범위 안에 있는 지뢰만 계산한다`() {
        val configuration = GameConfiguration(BoardSize(height = 3, width = 3), MineCount(3))
        val board = configuration.createBoard(fixedPlacement(Position(1, 0), Position(0, 1), Position(1, 1)))

        val result = board.snapshot().adjacentMineCountAt(Position(0, 0))

        assertThat(result).isEqualTo(AdjacentMineCount(3))
    }

    @Test
    fun `인접한 지뢰가 없으면 주변 지뢰 개수는 0이다`() {
        val configuration = GameConfiguration(BoardSize(height = 3, width = 3), MineCount(1))
        val board = configuration.createBoard(fixedPlacement(Position(2, 2)))

        val result = board.snapshot().adjacentMineCountAt(Position(0, 0))

        assertThat(result).isEqualTo(AdjacentMineCount(0))
    }

    private fun fixedPlacement(vararg positions: Position): MinePlacementStrategy =
        MinePlacementStrategy { _, _ -> MinePositions(positions.toList()) }
}
