package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class GameTest {
    @Test
    fun `게임을 시작하면 모든 칸이 닫혀 있다`() {
        val game = createGame(BoardSize(height = 2, width = 2), Position(1, 1))

        val snapshot = game.snapshot()

        assertThat(openStates(snapshot))
            .containsExactly(
                listOf(false, false),
                listOf(false, false),
            )
    }

    @Test
    fun `주변에 지뢰가 있는 안전 칸을 열면 해당 칸만 열린다`() {
        val game = createGame(BoardSize(height = 3, width = 3), Position(1, 1))

        game.open(Position(0, 0))

        val snapshot = game.snapshot()
        assertThat(openStates(snapshot))
            .containsExactly(
                listOf(true, false, false),
                listOf(false, false, false),
                listOf(false, false, false),
            )
        assertThat(snapshot.adjacentMineCountAt(Position(0, 0))).isEqualTo(AdjacentMineCount(1))
        assertThat(game.isInProgress()).isTrue()
    }

    @Test
    fun `주변 지뢰가 없는 칸을 열면 연결된 안전 칸을 열고 지뢰는 닫아 둔다`() {
        val minePosition = Position(2, 2)
        val game = createGame(BoardSize(height = 3, width = 3), minePosition)

        game.open(Position(0, 0))

        val snapshot = game.snapshot()
        assertThat(openStates(snapshot))
            .containsExactly(
                listOf(true, true, true),
                listOf(true, true, true),
                listOf(true, true, false),
            )
        assertThat(game.isWon()).isTrue()
    }

    @Test
    fun `지뢰 칸을 열면 게임에서 패배한다`() {
        val minePosition = Position(1, 1)
        val game = createGame(BoardSize(height = 2, width = 2), minePosition)

        game.open(minePosition)

        assertThat(game.isLost()).isTrue()
        assertThat(openStates(game.snapshot()))
            .containsExactly(
                listOf(false, false),
                listOf(false, true),
            )
    }

    @Test
    fun `모든 안전 칸을 열면 게임에서 승리한다`() {
        val game = createGame(BoardSize(height = 1, width = 2), Position(1, 0))

        game.open(Position(0, 0))

        assertThat(game.isWon()).isTrue()
    }

    @Test
    fun `보드 범위를 벗어난 위치는 열 수 없다`() {
        val game = createGame(BoardSize(height = 2, width = 2), Position(1, 1))

        assertThatThrownBy { game.open(Position(2, 0)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    private fun createGame(
        boardSize: BoardSize,
        minePosition: Position,
    ): Game {
        val configuration = GameConfiguration(boardSize, MineCount(1))
        val placement = MinePlacementStrategy { _, _ -> MinePositions(listOf(minePosition)) }
        return configuration.createGame(placement)
    }

    private fun openStates(snapshot: BoardSnapshot): List<List<Boolean>> =
        snapshot.mapRows(
            onClosed = { false },
            onMine = { true },
            onSafe = { true },
        )
}
