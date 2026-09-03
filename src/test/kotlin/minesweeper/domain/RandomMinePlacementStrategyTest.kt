package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class RandomMinePlacementStrategyTest {
    @Test
    fun `보드 범위 안에서 중복 없이 요청한 개수의 위치를 선택한다`() {
        val boardSize = BoardSize(height = 3, width = 3)
        val mineCount = MineCount(4)

        val minePositions = RandomMinePlacementStrategy().select(boardSize, mineCount)

        assertThat(minePositions.count()).isEqualTo(4)
        assertThat(minePositions.areWithin(boardSize)).isTrue()
    }

    @Test
    fun `같은 위치를 중복해 지뢰 위치로 만들 수 없다`() {
        val position = Position(0, 0)

        assertThatThrownBy { MinePositions(listOf(position, position)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
