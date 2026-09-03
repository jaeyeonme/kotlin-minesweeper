package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NeighborPositionsTest {
    @Test
    fun `중앙 위치는 기준 위치를 제외한 주변 8개 위치를 가진다`() {
        val boardSize = BoardSize(height = 3, width = 3)
        val center = Position(1, 1)

        val result = NeighborPositions.around(center, boardSize)

        assertThat(result.count()).isEqualTo(8)
        assertThat(result.contains(center)).isFalse()
    }

    @Test
    fun `모서리 위치는 보드 범위 안의 주변 3개 위치만 가진다`() {
        val boardSize = BoardSize(height = 3, width = 3)
        val corner = Position(0, 0)

        val result = NeighborPositions.around(corner, boardSize)

        assertThat(result.count()).isEqualTo(3)
        assertThat(result.contains(Position(1, 0))).isTrue()
        assertThat(result.contains(Position(0, 1))).isTrue()
        assertThat(result.contains(Position(1, 1))).isTrue()
    }
}
