package minesweeper.domain

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class GameConfigurationTest {
    @Test
    fun `높이가 1보다 작으면 게임 설정을 만들 수 없다`() {
        assertThatThrownBy { GameConfiguration(BoardSize(height = 0, width = 3), MineCount(1)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `너비가 1보다 작으면 게임 설정을 만들 수 없다`() {
        assertThatThrownBy { GameConfiguration(BoardSize(height = 2, width = 0), MineCount(1)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `지뢰 개수가 1보다 작으면 게임 설정을 만들 수 없다`() {
        assertThatThrownBy { GameConfiguration(BoardSize(height = 2, width = 3), MineCount(0)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `지뢰 개수가 전체 칸 수보다 많으면 게임 설정을 만들 수 없다`() {
        assertThatThrownBy { GameConfiguration(BoardSize(height = 2, width = 3), MineCount(7)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
