package minesweeper.controller

import minesweeper.domain.MinePlacementStrategy
import minesweeper.domain.MinePositions
import minesweeper.domain.Position
import minesweeper.view.InputView
import minesweeper.view.OutputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.ArrayDeque

class MinesweeperGameTest {
    @Test
    fun `안전 칸을 연 뒤 게임이 진행 중이면 변경된 보드를 출력하고 다음 좌표를 입력받는다`() {
        val inputs = ArrayDeque(listOf("2", "2", "1", "1, 1", "2, 2"))
        val outputs = mutableListOf<String>()
        val game = createGame(inputs, outputs)

        game.start(fixedPlacement(Position(1, 1)))

        assertThat(outputs)
            .containsExactly(
                "높이를 입력하세요.",
                "너비를 입력하세요.",
                "지뢰는 몇 개인가요?",
                "지뢰찾기 게임 시작",
                "open:",
                "1 C\nC C",
                "open:",
                "Lose Game.",
            )
    }

    @Test
    fun `마지막 안전 칸을 열면 변경된 보드를 출력하고 좌표 입력을 종료한다`() {
        val inputs = ArrayDeque(listOf("1", "2", "1", "1, 1"))
        val outputs = mutableListOf<String>()
        val game = createGame(inputs, outputs)

        game.start(fixedPlacement(Position(1, 0)))

        assertThat(outputs)
            .containsExactly(
                "높이를 입력하세요.",
                "너비를 입력하세요.",
                "지뢰는 몇 개인가요?",
                "지뢰찾기 게임 시작",
                "open:",
                "1 C",
            )
    }

    @Test
    fun `지뢰 칸을 선택하면 패배 메시지를 출력하고 좌표 입력을 종료한다`() {
        val inputs = ArrayDeque(listOf("1", "2", "1", "2, 1"))
        val outputs = mutableListOf<String>()
        val game = createGame(inputs, outputs)

        game.start(fixedPlacement(Position(1, 0)))

        assertThat(outputs)
            .containsExactly(
                "높이를 입력하세요.",
                "너비를 입력하세요.",
                "지뢰는 몇 개인가요?",
                "지뢰찾기 게임 시작",
                "open:",
                "Lose Game.",
            )
    }

    private fun createGame(
        inputs: ArrayDeque<String>,
        outputs: MutableList<String>,
    ): MinesweeperGame {
        val inputView = InputView(readLine = inputs::removeFirst, writeLine = outputs::add)
        val outputView = OutputView(writeLine = outputs::add)
        return MinesweeperGame(inputView, outputView)
    }

    private fun fixedPlacement(position: Position): MinePlacementStrategy =
        MinePlacementStrategy { _, _ -> MinePositions(listOf(position)) }
}
